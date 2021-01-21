package id.co.pqm.lms

import id.co.pqm.feature.onboard.data.usecases.OnBoardUseCase
import id.co.pqm.feature.onboard.data.usecases.OnBoardUseCaseImpl
import id.co.pqm.lib.mvvm.BaseVM
import id.co.pqm.lib.data.model.Mutable
import kotlinx.coroutines.delay

/**
 * Created by Kudzoza
 * on 18/01/2021
 **/

class MainVM(
    private val onBoardUseCase: OnBoardUseCase = OnBoardUseCaseImpl.getInstance()
) : BaseVM() {

    val isAppReady = Mutable(0)

    override suspend fun onCreate() {
        delay(2500)
        isAppReady.content = if (onBoardUseCase.isOnBoardAvailable()) 1 else 2
    }
}