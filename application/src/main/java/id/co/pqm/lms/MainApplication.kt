package id.co.pqm.lms

import id.co.chpn.feature.auth.screen.login.LoginActivity
import id.co.pqm.feature.onboard.screen.onboard.OnBoardActivity
import id.co.pqm.feature.home.screen.home.HomeActivity
import id.co.pqm.lib.module.AuthModule
import id.co.pqm.lib.module.HomeModule
import id.co.pqm.lib.module.OnBoardModule
import id.co.pqm.lib.mvvm.BaseApplication
import id.co.pqm.lib.mvvm.BaseModule

/**
 * Created by Kudzoza
 * on 19/Dec/2020
 **/
class MainApplication : BaseApplication() {

    override val listModule: List<BaseModule>
        get() = listOf(
            OnBoardModule.get(),
            AuthModule.get(),
            HomeModule.get()
        )

    override fun onCreate() {
        super.onCreate()
        listModule.apply {
            forEach { it.baseUrlCaptor = { BuildConfig.BASE_URL } }
        }

        OnBoardModule.get().apply {
            open = { OnBoardActivity::class.java }
        }

        AuthModule.get().apply {
            open = { LoginActivity::class.java }
        }

        HomeModule.get().apply {
            open = { HomeActivity::class.java }
        }
    }


}