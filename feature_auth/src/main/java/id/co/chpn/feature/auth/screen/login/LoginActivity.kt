package id.co.chpn.feature.auth.screen.login

import id.co.chpn.feature.databinding.ActivityLoginBinding
import id.co.pqm.lib.mvvm.BaseActivity

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>(
    ActivityLoginBinding::inflate,
    LoginVM::class.java
) {

    override fun onViewReady() {

    }

    override fun observeLiveData() {

    }
}