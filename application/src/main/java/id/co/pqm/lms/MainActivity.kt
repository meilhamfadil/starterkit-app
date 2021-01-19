package id.co.pqm.lms

import id.co.chpn.feature.auth.screen.login.LoginActivity
import id.co.pqm.lib.module.AuthModule
import id.co.pqm.lib.module.HomeModule
import id.co.pqm.lib.module.OnBoardModule
import id.co.pqm.lib.mvvm.BaseActivity
import id.co.pqm.lib.mvvm.util.openFreshModule
import id.co.pqm.lib.mvvm.util.watch
import id.co.pqm.lms.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(
    ActivityMainBinding::inflate,
    MainVM::class.java
) {

    override fun onViewReady() {

    }

    override fun observeLiveData() {
        watch(vm.isAppReady) {
            if (it == 1)
                openFreshModule(
                    OnBoardModule.get(),
                    OnBoardModule::open
                )
            else if (it == 2)
                openFreshModule(
                    AuthModule.get(),
                    AuthModule::open
                )
        }
    }
}
