package id.kudzoza.feature.dummy.screen.home

import android.os.Bundle
import id.kudzoza.feature.dummy.R
import id.kudzoza.feature.dummy.databinding.FragmentHomeBinding
import id.kudzoza.lib.mvvm.BaseFragment
import id.kudzoza.lib.mvvm.util.Observe

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
        Observe(vm.user) {
            binding.userWelcome.text = getString(R.string.label_welcome, it)
        }
    }

    override fun getViewModel() = HomeVM::class.java
}