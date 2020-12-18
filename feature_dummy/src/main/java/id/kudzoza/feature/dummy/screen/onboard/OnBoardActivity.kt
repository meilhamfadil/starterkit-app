package id.kudzoza.feature.dummy.screen.onboard

import id.kudzoza.feature.dummy.databinding.ActivityOnboardBinding
import id.kudzoza.feature.dummy.screen.home.HomeActivity
import id.kudzoza.lib.mvvm.BaseActivity
import id.kudzoza.lib.mvvm.util.Observe
import id.kudzoza.lib.mvvm.util.showToast

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardActivity : BaseActivity<ActivityOnboardBinding, OnBoardVM>(
    ActivityOnboardBinding::inflate
) {

    override fun onViewReady() {
        binding.label1.setOnClickListener {
            vm.label2.content = "My Home"
        }
        binding.label3.setOnClickListener {
            openActivity(HomeActivity::class.java)
        }
    }

    override fun observeLiveData() {
        Observe(vm.label1) {
            binding.label1.text = it
        }
        Observe(vm.label2) {
            binding.label2.text = it
        }
        Observe(vm.label3) {
            binding.label3.text = it
        }
    }


    override fun getViewModel() = OnBoardVM::class.java
}