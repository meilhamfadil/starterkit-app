package id.co.lib.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
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
 * on 18/Dec/2020
 **/

abstract class BaseActivity<B : ViewBinding, VM : BaseVM>(
    private val viewBinder: (LayoutInflater) -> B,
    private val viewModelClazz: Class<VM>
) : AppCompatActivity(), CoroutineScope, ScreenContract {

    private val job: Job = SupervisorJob()
    private var isVMCreated = false
    private var isViewCreated = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    val binding by lazy {
        viewBinder.invoke(layoutInflater)
    }
    val vm: VM by lazy {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(viewModelClazz)
    }

    abstract fun onViewReady()
    abstract fun observeLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!isViewCreated) {
            onViewReady()
            observeLiveData()
            isViewCreated = true
        }
    }

    override fun onStart() {
        super.onStart()
        launch {
            if (!isVMCreated) {
                vm.onCreate()
                isVMCreated = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.registerToast(this) { msg, resMsg ->
            if (msg.isNotEmpty()) showToast(msg)
            else if (resMsg != -1) showToast(resMsg)
        }

        if (intent.extras != null)
            onReceivedData(intent.extras ?: Bundle())
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

    open fun onReceivedData(dataResult: Bundle) {}

    override fun screenContext(): Context = applicationContext

    override fun lifeCycleOwner(): LifecycleOwner = this@BaseActivity

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