package id.kudzoza.feature.home.screen.home

import android.os.Bundle
import id.kudzoza.feature.home.R
import id.kudzoza.feature.home.databinding.FragmentHomeBinding
import id.kudzoza.lib.module.HomeModule
import id.kudzoza.lib.mvvm.BaseFragment
import id.kudzoza.lib.mvvm.util.watch
import id.kudzoza.lib.mvvm.util.areVisible
import id.kudzoza.lib.mvvm.util.showToast

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>(
    FragmentHomeBinding::inflate
) {

    override fun onViewReady() {
    }

    override fun observeLiveData() {
        watch(vm.user) {
            binding.userWelcome.text = getString(R.string.label_welcome, it)
        }

        watch(vm.isLoading) {
            binding.progress.visibility = it.areVisible()
            binding.userWelcome.visibility = (!it).areVisible()
        }
    }

    override fun onReceivedData(dataResult: Bundle) {
        super.onReceivedData(dataResult)
        dataResult.getString(HomeModule.DATA_ID).let {
            showToast(it ?: "Kudzoza")
        }
    }

    override fun getViewModel() = HomeVM::class.java
}