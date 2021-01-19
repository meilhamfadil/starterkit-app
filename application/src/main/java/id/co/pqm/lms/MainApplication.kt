package id.co.pqm.lms

import id.co.feature.dummy.screen.onboard.OnBoardActivity
import id.co.feature.home.screen.home.HomeActivity
import id.co.lib.module.HomeModule
import id.co.lib.module.OnBoardModule
import id.co.lib.mvvm.BaseApplication

/**
 * Created by Kudzoza
 * on 19/Dec/2020
 **/
class MainApplication : BaseApplication() {

    override fun appendModule() = listOf(
        HomeModule.get(),
        OnBoardModule.get()
    )

    override fun onCreate() {
        super.onCreate()

        HomeModule.get().apply {
            open = { HomeActivity::class.java }
        }

        OnBoardModule.get().apply {
            open = { OnBoardActivity::class.java }
        }
    }


}