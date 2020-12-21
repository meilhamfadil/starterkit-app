package id.kudzoza.feature.home.data.usecase

import id.kudzoza.feature.home.data.repository.HomeRepository
import id.kudzoza.feature.home.data.repository.HomeRepositoryImpl
import id.kudzoza.lib.data.model.Payload
import id.kudzoza.lib.data.service.Completable

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