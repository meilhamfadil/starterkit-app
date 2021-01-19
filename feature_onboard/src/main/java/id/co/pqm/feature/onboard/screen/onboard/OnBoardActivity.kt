package id.co.pqm.feature.onboard.screen.onboard

import id.co.pqm.feature.onboard.databinding.ActivityOnboardBinding
import id.co.pqm.lib.module.AuthModule
import id.co.pqm.lib.module.HomeModule
import id.co.pqm.lib.mvvm.BaseActivity
import id.co.pqm.lib.mvvm.util.openFreshModule
import id.co.pqm.lib.mvvm.util.watch

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardActivity : BaseActivity<ActivityOnboardBinding, OnBoardVM>(
    ActivityOnboardBinding::inflate,
    OnBoardVM::class.java
) {

    override fun onViewReady() {
        binding.takeMeButton.setOnClickListener { vm.finishOnBoard() }
    }

    override fun observeLiveData() {
        watch(vm.isOnBoardFinish) {
            openFreshModule(
                AuthModule.get(),
                AuthModule::open
            )
        }
    }
}