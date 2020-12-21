package id.kudzoza.lib.module

import android.os.Bundle
import id.kudzoza.lib.mvvm.BaseModule

/**
 * Created by Kudzoza
 * on 20/12/2020
 **/

class HomeModule : BaseModule() {

    lateinit var open: (() -> Class<*>)

    companion object {
        private val INSTANCE by lazy { HomeModule() }
        const val DATA_ID = "DATA_ID_@@"

        fun get() = INSTANCE

        fun homeData(id: String) = Bundle().apply {
            putString(DATA_ID, id)
        }
    }
}