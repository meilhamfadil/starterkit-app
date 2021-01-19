package id.co.lib.ui.util

import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import id.co.lib.mvvm.contract.ScreenContract
import id.co.lib.mvvm.data.Mutable

/**
 * Created by Kudzoza
 * on 18/01/2021
 **/

fun Boolean.areVisible() = if (this) View.VISIBLE else View.GONE

fun Any?.nullIsVisible() = if (this == null) View.VISIBLE else View.GONE

fun Any?.notNullIsVisible() = if (this != null) View.VISIBLE else View.GONE
