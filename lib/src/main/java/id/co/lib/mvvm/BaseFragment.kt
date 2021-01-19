package id.co.lib.mvvm

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
import id.co.lib.mvvm.contract.ScreenContract
import id.co.lib.mvvm.util.showToast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KProperty1

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

abstract class BaseFragment<B : ViewBinding, VM : BaseVM>(
    private val viewBinder: (LayoutInflater) -> B,
    private val viewModelClazz: Class<VM>
) : Fragment(), CoroutineScope, ScreenContract {

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
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(viewModelClazz)
    }
    val navigator by lazy {
        Navigati
    }

    abstract fun onViewReady()
    abstract fun observeLiveData()

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

    override fun openActivity(
        target: Class<*>,
        extras: Bundle?,
        clear: Boolean
    ) {
        Intent(screenContext(), target).let {
            if (clear) it.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            extras?.let { b -> it.putExtras(b) }
            startActivity(it)
        }
    }

    override fun <M : BaseModule> openModule(
        module: M,
        target: KProperty1<M, () -> Class<*>>,
        extras: Bundle?,
        clear: Boolean
    ) {
        Intent(screenContext(), target.get(module).invoke()).let {
            if (clear) it.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            extras?.let { b -> it.putExtras(b) }
            startActivity(it)
        }
    }
}