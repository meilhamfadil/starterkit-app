package id.co.chpn.feature.auth.data.model

import id.co.pqm.lib.data.model.BaseForm
import id.co.pqm.lib.data.model.Mutable
import id.co.pqm.lib.ui.getValue

/**
 * Created by CHPN-RND
 * on 21/01/2021
 **/
class LoginFormModel : BaseForm() {

    val username = Mutable("")
    val password = Mutable("")
    private val _username by username::content
    private val _password by password::content

    fun validate() {
        isValid.content = false
        isValid.content = _username.isNotEmpty() && _password.isNotEmpty()
    }

}