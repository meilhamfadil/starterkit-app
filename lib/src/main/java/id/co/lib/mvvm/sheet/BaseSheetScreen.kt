package id.co.lib.mvvm.sheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.co.lib.mvvm.BaseVM
import id.co.lib.mvvm.R
import id.co.lib.mvvm.contract.ScreenContract
import id.co.lib.mvvm.util.showToast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseSheetScreen<B : ViewBinding, VM : BaseVM>(
    private val viewBinder: (LayoutInflater) -> ViewBinding
) : BottomSheetDialogFragment(), CoroutineScope, ScreenContract {

    private val job = SupervisorJob()
    private var isHasCreated = false
    private var hasCallViewCreated = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    val binding by lazy { viewBinder.invoke(LayoutInflater.from(requireContext())) as B }
    val vm: VM by lazy {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
            getViewModel()
        )
    }
    internal var extras: Bundle? = null

    abstract fun onViewReady()

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            if (!isHasCreated) {
                vm.onCreate()
                isHasCreated = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.registerToast(this) { msg, resMsg ->
            if (msg.isNotEmpty()) showToast(msg)
            else if (resMsg != -1) showToast(resMsg)
        }
        if (extras != null) {
            onReceivedData(extras)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReceivedData(extras)

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels * 0.9

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);

        view.viewTreeObserver.addOnGlobalLayoutListener {
            (dialog?.findViewById(R.id.design_bottom_sheet) as FrameLayout?)?.let { bottomSheet ->
                bottomSheet.background = ColorDrawable(Color.TRANSPARENT);
                bottomSheet.layoutParams.height = height.toInt()
                val behavior: BottomSheetBehavior<FrameLayout> =
                    BottomSheetBehavior.from(bottomSheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.peekHeight = 0
                behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {

                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN || newState == BottomSheetBehavior.STATE_COLLAPSED) {
                            dismiss()
                        }
                    }
                })
                if (!hasCallViewCreated) {
                    onViewReady()
                    hasCallViewCreated = true
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        destroy()
    }

    open fun onReceivedData(dataResult: Bundle?) {}

    override fun screenContext() = requireContext()
}