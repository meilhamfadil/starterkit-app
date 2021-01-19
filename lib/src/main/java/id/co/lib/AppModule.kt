package id.co.lib

import id.co.lib.mvvm.BaseModule

/**
 * Created by Kudzoza
 * on 19/12/2020
 **/

class AppModule : BaseModule() {



    companion object {
        private val INSTANCE by lazy { AppModule() }
        fun get() =
            INSTANCE
    }

}