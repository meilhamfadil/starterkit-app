package id.co.pqm.lib.ui.util

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import id.co.pqm.lib.data.model.Mutable


/**
 * Created by Kudzoza
 * on 18/01/2021
 **/

fun Boolean.areVisible() = if (this) View.VISIBLE else View.GONE

fun Any?.nullIsVisible() = if (this == null) View.VISIBLE else View.GONE

fun Any?.notNullIsVisible() = if (this != null) View.VISIBLE else View.GONE

fun Activity.hideSoftKeyboard() {
    (this.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
        this.currentFocus?.windowToken,
        0
    )
}

fun EditText.bindTo(mutable: Mutable<String>) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            mutable.content = s?.toString().orEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}