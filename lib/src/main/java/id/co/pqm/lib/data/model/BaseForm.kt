package id.co.pqm.lib.data.model

/**
 * Created by CHPN-RND
 * on 21/01/2021
 **/
abstract class BaseForm(private val valid: Boolean = false) {
    val isValid = Mutable(valid)
}