package id.kudzoza.feature.dummy.screen.home

import id.kudzoza.lib.mvvm.BaseVM
import id.kudzoza.lib.mvvm.util.Mutable

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class HomeVM : BaseVM() {

    val user = Mutable("")

    override suspend fun onCreate() {
        initData()
    }

    private fun initData() = launch {
        user.content = "Kudzoza"
    }
}