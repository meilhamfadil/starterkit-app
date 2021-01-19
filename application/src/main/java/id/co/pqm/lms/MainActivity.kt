package id.co.pqm.lms

import id.co.lib.module.OnBoardModule
import id.co.lib.mvvm.BaseActivity
import id.co.lib.mvvm.util.openFreshModule
import id.co.lib.mvvm.util.watch
import id.co.pqm.lms.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(
    ActivityMainBinding::inflate,
    MainVM::class.java
) {

    override fun onViewReady() {
    }

    override fun observeLiveData() {
        watch(vm.isAppReady) {
            openFreshModule(
                OnBoardModule.get(),
                OnBoardModule::open
            )
        }
    }
}
