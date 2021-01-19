package id.co.pqm.lms

import id.co.lib.mvvm.BaseVM
import id.co.lib.mvvm.data.Mutable
import kotlinx.coroutines.delay

/**
 * Created by Kudzoza
 * on 18/01/2021
 **/

class MainVM : BaseVM() {

    val isAppReady = Mutable(false)

    override suspend fun onCreate() {
        delay(800)
        isAppReady.content = true
    }
}