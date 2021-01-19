package id.co.pqm.feature.onboard.data.usecases

import id.co.pqm.feature.onboard.data.repositories.OnBoardRepository
import id.co.pqm.feature.onboard.data.repositories.OnBoardRepositoryImpl

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/

class OnBoardUseCaseImpl(
    private val onBoardRepository: OnBoardRepository = OnBoardRepositoryImpl.getInstance()
) : OnBoardUseCase {

    override fun isOnBoardAvailable(): Boolean = onBoardRepository.isOnBoardAvailable().data ?: true

    override fun finishOnBoard(): Boolean = onBoardRepository.finishOnBoard().data ?: false

    companion object {
        private val INSTANCE = OnBoardUseCaseImpl()
        fun getInstance() = INSTANCE
    }
}