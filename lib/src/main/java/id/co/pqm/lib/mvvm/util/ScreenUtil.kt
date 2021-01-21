package id.co.pqm.lib.mvvm.util

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import id.co.pqm.lib.data.model.Mutable
import id.co.pqm.lib.mvvm.BaseModule
import id.co.pqm.lib.mvvm.contract.ScreenContract
import kotlin.reflect.KProperty1


/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

// Toast
fun ScreenContract.showToast(value: String, length: Int = Toast.LENGTH_SHORT) {
    screenContext().let { Toast.makeText(it, value, length).show() }
}

fun ScreenContract.showToast(@StringRes value: Int, length: Int = Toast.LENGTH_SHORT) {
    screenContext().let { Toast.makeText(it, value, length).show() }
}

// VM
fun <T> ScreenContract.watch(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(lifeCycleOwner(), Observer { observer.invoke(it) })
}

// Resource
fun ScreenContract.getColorResource(color: Int) =
    ContextCompat.getColor(this.screenContext(), color)

fun ScreenContract.getDrawableResource(drawable: Int) =
    ContextCompat.getDrawable(this.screenContext(), drawable)

fun Context.getColorCompat(color: Int) =
    ContextCompat.getColor(this, color)

fun Context.getDrawableCompat(drawable: Int) =
    ContextCompat.getDrawable(this, drawable)

// Action
fun ScreenContract.openFreshActivity(
    target: Class<*>,
    extras: Bundle? = null
) {
    this.openActivity(target, extras, true)
}

fun <M : BaseModule> ScreenContract.openFreshModule(
    module: M,
    target: KProperty1<M, () -> Class<*>>,
    extras: Bundle? = null
) {
    this.openModule(module, target, extras, true)
}