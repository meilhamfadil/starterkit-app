package id.kudzoza.feature.dummy.data.repositories

import id.kudzoza.lib.data.model.BaseResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
interface  MovieRepository {
    suspend fun fetchData(): BaseResponse<String>
}