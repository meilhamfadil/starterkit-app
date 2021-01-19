package id.co.pqm.lib.ui.util

import android.view.View

/**
 * Created by Kudzoza
 * on 18/01/2021
 **/

fun Boolean.areVisible() = if (this) View.VISIBLE else View.GONE

fun Any?.nullIsVisible() = if (this == null) View.VISIBLE else View.GONE

fun Any?.notNullIsVisible() = if (this != null) View.VISIBLE else View.GONE
