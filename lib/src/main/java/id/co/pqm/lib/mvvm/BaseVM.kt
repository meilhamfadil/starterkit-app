package id.co.pqm.lib.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.*
import id.co.pqm.lib.data.model.Mutable
import kotlinx.coroutines.*
import kotlinx.coroutines.launch as fire
import kotlinx.coroutines.async as await
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM : ViewModel(), CoroutineScope {

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val toastLiveData by lazy { Mutable("") }
    private val toastResLiveData by lazy { Mutable(-1) }

    abstract suspend fun onCreate()

    fun showToast(message: String) {
        if (message.isNotEmpty())
            toastLiveData.content = message
    }

    fun showToast(@StringRes messageRes: Int) {
        if (messageRes != -1)
            toastResLiveData.content = messageRes
    }

    fun registerToast(lifecycleOwner: LifecycleOwner, observer: (String, Int) -> Unit) {
        toastLiveData.observe(lifecycleOwner, Observer { observer(it, -1) })
        toastResLiveData.observe(lifecycleOwner, Observer { observer("", it) })
    }

    fun unRegisterMessage(lifecycleOwner: LifecycleOwner) {
        toastLiveData.removeObservers(lifecycleOwner)
        toastResLiveData.removeObservers(lifecycleOwner)
    }

    fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> Unit
    ) =
        fire(dispatcher) {
            command.invoke()
        }

    fun <T> async(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> T
    ) =
        await(dispatcher) {
            command.invoke()
        }
}