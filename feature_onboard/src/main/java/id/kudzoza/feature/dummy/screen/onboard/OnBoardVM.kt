package id.kudzoza.feature.dummy.screen.onboard

import id.kudzoza.lib.mvvm.BaseVM
import id.kudzoza.lib.mvvm.data.Mutable

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardVM : BaseVM() {

    val source = Mutable(0)
    val done = Mutable(0)

    override suspend fun onCreate() {

    }

    fun incResource() = launch {
        source.content += 1
    }

    fun incDone() = launch {
        done.content += 1
    }

}