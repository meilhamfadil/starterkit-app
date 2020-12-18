package id.kudzoza.feature.dummy.data.usecases

import id.kudzoza.feature.dummy.data.repositories.MovieRepository
import id.kudzoza.feature.dummy.data.repositories.MovieRepositoryImpl
import id.kudzoza.feature.dummy.data.repositories.UserRepository
import id.kudzoza.feature.dummy.data.repositories.UserRepositoryImpl
import id.kudzoza.lib.data.model.ApiResult
import id.kudzoza.lib.data.source.UseCase

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
class HomeUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : UseCase {

    suspend fun getHomeData(): ApiResult<String> {
        var userData = ""
        var movieData = ""
        var isSuccess: Boolean
        var message = ""

        userRepository.fetchData().let {
            isSuccess = it.code == 200
            if (!isSuccess) message = it.message

            if (it.code == 200 && it.data != null)
                userData = it.data.orEmpty()

        }

        movieRepository.fetchData().let {
            isSuccess = it.code == 200 && isSuccess
            if (!isSuccess) message = it.message

            if (it.code == 200 && it.data != null)
                movieData = it.data.orEmpty()
        }

        return ApiResult(
            isSuccess,
            if (isSuccess) "$userData - $movieData" else "",
            message
        )
    }

}