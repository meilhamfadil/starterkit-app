package id.kudzoza.lib.mvvm.contract

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
interface ScreenContract {

    fun lifeCycleOwner(): LifecycleOwner

    fun screenContext(): Context

    fun openActivity(target: Class<*>, data: Bundle? = null, clear: Boolean = false)

    fun openSheet()

    fun openFragment()

}