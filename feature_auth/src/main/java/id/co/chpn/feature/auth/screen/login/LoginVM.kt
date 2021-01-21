package id.co.chpn.feature.auth.screen.login

import id.co.chpn.feature.auth.data.model.LoginFormModel
import id.co.pqm.lib.mvvm.BaseVM

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
class LoginVM : BaseVM() {

    val loginForm = LoginFormModel()

    override suspend fun onCreate() {}
}