package id.kudzoza.feature.dummy.screen.onboard

import id.kudzoza.lib.mvvm.BaseVM
import id.kudzoza.lib.mvvm.util.Mutable
import kotlinx.coroutines.delay

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardVM : BaseVM() {

    val label1 = Mutable("")
    val label2 = Mutable("")
    val label3 = Mutable("")

    override suspend fun onCreate() {
        initData()
    }

    private fun initData() = launch {
        delay(1000)
        label1.content = "Welcome to"
        delay(1000)
        label2.content = "The Jungle"
        delay(3000)
        label3.content = "Thanks for Waiting\n Click to go Home"
    }

}