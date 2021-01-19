package id.co.pqm.lib.mvvm.data

import androidx.lifecycle.LiveData

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class Mutable<T>(private val defValue: T) : LiveData<T>() {

    var content: T = defValue
        get() = value ?: defValue
        set(param) {
            field = param
            postValue(param)
        }

}