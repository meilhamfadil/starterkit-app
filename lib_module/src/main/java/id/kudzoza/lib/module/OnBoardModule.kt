package id.kudzoza.lib.module

import id.kudzoza.lib.mvvm.BaseModule

/**
 * Created by Kudzoza
 * on 20/12/2020
 **/

class OnBoardModule : BaseModule() {

    lateinit var open: (() -> Class<*>)

    companion object{
        private val INSTANCE by lazy { OnBoardModule() }
        fun get() = INSTANCE
    }

}