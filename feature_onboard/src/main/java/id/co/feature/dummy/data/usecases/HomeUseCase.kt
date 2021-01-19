package id.co.feature.dummy.data.usecases

import id.co.feature.dummy.data.repositories.MovieRepository
import id.co.feature.dummy.data.repositories.MovieRepositoryImpl
import id.co.feature.dummy.data.repositories.UserRepository
import id.co.feature.dummy.data.repositories.UserRepositoryImpl
import id.co.lib.data.model.ApiResult
import id.co.lib.data.usecase.UseCase

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