package id.co.pqm.lib.mvvm.contract

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import id.co.pqm.lib.mvvm.BaseModule
import kotlin.reflect.KProperty1

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
interface ScreenContract {
    fun lifeCycleOwner(): LifecycleOwner

    fun screenContext(): Context

    fun openActivity(
        target: Class<*>,
        extras: Bundle? = null,
        clear: Boolean = false
    )

    fun <M : BaseModule> openModule(
        module: M,
        target: KProperty1<M, () -> Class<*>>,
        extras: Bundle? = null,
        clear: Boolean = false
    )
}