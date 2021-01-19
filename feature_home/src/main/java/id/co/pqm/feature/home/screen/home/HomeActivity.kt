package id.co.pqm.feature.home.screen.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.co.pqm.feature.home.R
import id.co.pqm.feature.home.databinding.ActivityHomeBinding
import id.co.pqm.lib.mvvm.BaseActivity

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>(
    ActivityHomeBinding::inflate,
    HomeVM::class.java
) {

    override fun onViewReady() {
        binding.userWelcome.text = "Welcome Bro."
    }

    override fun observeLiveData() {

    }
}