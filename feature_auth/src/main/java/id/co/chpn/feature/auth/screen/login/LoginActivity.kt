package id.co.chpn.feature.auth.screen.login

import id.co.chpn.feature.databinding.ActivityLoginBinding
import id.co.pqm.lib.mvvm.BaseActivity
import id.co.pqm.lib.mvvm.util.showToast
import id.co.pqm.lib.mvvm.util.watch
import id.co.pqm.lib.ui.util.bindTo
import id.co.pqm.lib.ui.util.hideSoftKeyboard

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>(
    ActivityLoginBinding::inflate,
    LoginVM::class.java
) {

    override fun onViewReady() {
        binding.fieldUsername.bindTo(vm.loginForm.username)
        binding.fieldPassword.bindTo(vm.loginForm.password)

        binding.container.setOnClickListener { hideSoftKeyboard() }

        binding.btnLogin.setOnClickListener {
            showToast("${vm.loginForm.username.content} has Clicked Login")
        }
    }

    override fun observeLiveData() {
        watch(vm.loginForm.username) { vm.loginForm.validate() }
        watch(vm.loginForm.password) { vm.loginForm.validate() }
    }
}