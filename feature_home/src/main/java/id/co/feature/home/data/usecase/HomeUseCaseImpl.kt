package id.co.feature.home.data.usecase

import id.co.feature.home.data.repository.HomeRepository
import id.co.feature.home.data.repository.HomeRepositoryImpl
import id.co.lib.data.service.Completable

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

class HomeUseCaseImpl(
    private val homeRepository: HomeRepository = HomeRepositoryImpl.getInstance()
) : HomeUseCase {

    override suspend fun getUsername() = Completable(homeRepository.getUsername())

    companion object {
        private val INSTANCE = HomeUseCaseImpl()
        fun getInstance() = INSTANCE
    }
}