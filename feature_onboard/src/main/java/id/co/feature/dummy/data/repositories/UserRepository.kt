package id.co.feature.dummy.data.repositories

import id.co.lib.data.model.BaseResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
interface UserRepository {

    suspend fun fetchData(): BaseResponse<String>

}