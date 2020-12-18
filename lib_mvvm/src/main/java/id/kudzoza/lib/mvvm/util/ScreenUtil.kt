package id.kudzoza.lib.mvvm.util

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import id.kudzoza.lib.mvvm.contract.ScreenContract

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

fun ScreenContract.showToast(value: String, length: Int = Toast.LENGTH_SHORT) {
    screenContext().let { Toast.makeText(it, value, length).show() }
}

fun ScreenContract.showToast(@StringRes value: Int, length: Int = Toast.LENGTH_SHORT) {
    screenContext().let { Toast.makeText(it, value, length).show() }
}

fun <T> ScreenContract.Observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(lifeCycleOwner(), Observer { observer.invoke(it) })
}