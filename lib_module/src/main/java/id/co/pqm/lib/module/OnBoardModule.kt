package id.co.pqm.lib.module

import id.co.pqm.lib.mvvm.BaseModule

/**
 * Created by Kudzoza
 * on 20/12/2020
 **/

class OnBoardModule : BaseModule() {

    lateinit var open: (() -> Class<*>)

    companion object {
        private val INSTANCE by lazy { OnBoardModule() }

        fun get() = INSTANCE
    }

}