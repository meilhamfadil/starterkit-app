package id.co.lib.mvvm.util

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import id.co.lib.mvvm.BaseModule
import id.co.lib.mvvm.contract.ScreenContract
import id.co.lib.mvvm.data.Mutable
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

fun ScreenContract.bind(editText: EditText, mutable: Mutable<String>) {
    mutable.observe(this.lifeCycleOwner(), Observer { editText.setText(it) })
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            mutable.content = s?.toString().orEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

// Resource
fun ScreenContract.getColorResource(color: Int) =
    ContextCompat.getColor(this.screenContext(), color)

fun ScreenContract.getDrawableResource(drawable: Int) =
    ContextCompat.getDrawable(this.screenContext(), drawable)

fun Context.getColorResource(color: Int) =
    ContextCompat.getColor(this, color)

fun Context.getDrawableResource(drawable: Int) =
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