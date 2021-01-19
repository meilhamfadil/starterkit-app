package id.co.lib.data.model

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
data class ApiResult<T>(
    val isSuccess: Boolean = true,
    val message: String = "OK",
    val data: T? = null,
    val rawResponse: BaseResponse<*>? = null
)