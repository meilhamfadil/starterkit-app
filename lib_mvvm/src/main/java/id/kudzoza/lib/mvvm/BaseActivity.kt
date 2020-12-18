package id.kudzoza.lib.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
 * on 18/Dec/2020
 **/

abstract class BaseActivity<B : ViewBinding, VM : BaseVM>(
    private val viewBinder: (LayoutInflater) -> B
) :
    AppCompatActivity(), CoroutineScope, ScreenContract {

    private val job: Job = SupervisorJob()
    private var isVMCreated = false
    private var isViewCreated = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    val binding by lazy { viewBinder.invoke(LayoutInflater.from(applicationContext)) }
    val vm: VM by lazy {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(getViewModel())
    }

    internal var extras: Bundle? = null
    abstract fun onViewReady()
    abstract fun observeLiveData()
    abstract fun getViewModel(): Class<VM>

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

        if (extras != null)
            onReceivedData(extras ?: Bundle())
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

    open fun onReceivedData(dataResult: Bundle) {}

    override fun screenContext(): Context = applicationContext

    override fun lifeCycleOwner(): LifecycleOwner = this@BaseActivity

    override fun openActivity(target: Class<*>, data: Bundle?, clear: Boolean) {
        startActivity(Intent(applicationContext, target).apply {
            if (data != null)
                putExtras(data)
        })
        if (clear)
            finish()
    }

    override fun openSheet() {

    }

    override fun openFragment() {

    }

}