package id.kudzoza.lib.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import id.kudzoza.lib.mvvm.contract.ScreenContract
import id.kudzoza.lib.mvvm.util.showToast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

abstract class BaseFragment<B : ViewBinding, VM : BaseVM>(private val viewBinder: (LayoutInflater) -> B) :
    Fragment(), CoroutineScope,
    ScreenContract {

    private val job = SupervisorJob()
    private var isVMCreated = false
    private var isViewCreated = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    val binding: B by lazy {
        viewBinder.invoke(LayoutInflater.from(requireContext()))
    }
    val vm: VM by lazy {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(getViewModel())
    }

    abstract fun onViewReady()
    abstract fun observeLiveData()
    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            if (!isVMCreated) {
                vm.onCreate()
                isVMCreated = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        vm.registerToast(this) { msg, resMsg ->
            if (msg.isNotEmpty()) showToast(msg)
            else if (resMsg != -1) showToast(resMsg)
        }
        if (arguments != null)
            onReceivedData(arguments ?: Bundle())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isViewCreated) {
            onViewReady()
            observeLiveData()
            isViewCreated = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

    open fun onReceivedData(dataResult: Bundle) {}

    override fun lifeCycleOwner(): LifecycleOwner = this@BaseFragment

    override fun screenContext() = requireContext()

    override fun openActivity(target: Class<*>, data: Bundle?, clear: Boolean) {
        startActivity(Intent(screenContext(), target).apply {
            if (data != null)
                putExtras(data)
        })
        if (clear)
            activity?.finish()
    }

    override fun openSheet() {

    }

    override fun openFragment() {

    }
}