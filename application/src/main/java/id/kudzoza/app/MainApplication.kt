package id.kudzoza.app

import id.kudzoza.feature.dummy.screen.onboard.OnBoardActivity
import id.kudzoza.feature.home.screen.home.HomeActivity
import id.kudzoza.lib.AppModule
import id.kudzoza.lib.module.HomeModule
import id.kudzoza.lib.module.OnBoardModule
import id.kudzoza.lib.mvvm.BaseApplication
import id.kudzoza.lib.mvvm.BaseModule

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