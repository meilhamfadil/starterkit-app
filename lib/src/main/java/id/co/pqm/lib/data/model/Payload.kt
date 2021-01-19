package id.co.pqm.lib.data.model

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
data class Payload<T>(
    val isSuccess: Boolean = true,
    val message: String = "OK",
    val data: T? = null
)