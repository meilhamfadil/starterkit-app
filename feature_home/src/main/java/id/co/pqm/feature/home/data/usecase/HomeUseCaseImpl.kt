package id.co.pqm.feature.home.data.usecase

import id.co.pqm.feature.home.data.repository.HomeRepository
import id.co.pqm.feature.home.data.repository.HomeRepositoryImpl
import id.co.pqm.lib.data.model.Payload

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

class HomeUseCaseImpl(
    private val homeRepository: HomeRepository = HomeRepositoryImpl.getInstance()
) : HomeUseCase {

    override suspend fun getUsername(): Payload<String> = homeRepository.getUsername()

    companion object {
        private val INSTANCE = HomeUseCaseImpl()
        fun getInstance() = INSTANCE
    }
}