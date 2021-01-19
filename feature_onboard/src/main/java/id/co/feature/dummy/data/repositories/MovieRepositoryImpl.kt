package id.co.feature.dummy.data.repositories

import id.co.lib.data.model.BaseResponse
import id.co.lib.AppModule
import kotlin.random.Random

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
class MovieRepositoryImpl(
    private val token: String = AppModule.get().token.orEmpty()
) : MovieRepository {

    override suspend fun fetchData(): BaseResponse<String> {
        val random = Random(100)
        return if (random.nextInt() % 2 == 0) {
            BaseResponse(200, "Avengers", "success")
        } else {
            BaseResponse(422, null, "error fetch data from the server")
        }

    }
}