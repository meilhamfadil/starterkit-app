package id.co.pqm.feature.onboard.screen.onboard

import id.co.pqm.feature.onboard.data.usecases.OnBoardUseCase
import id.co.pqm.feature.onboard.data.usecases.OnBoardUseCaseImpl
import id.co.pqm.lib.mvvm.BaseVM
import id.co.pqm.lib.data.model.Mutable

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class OnBoardVM(
    private val onBoardUseCase: OnBoardUseCase = OnBoardUseCaseImpl.getInstance()
) : BaseVM() {

    val isOnBoardFinish = Mutable(false)

    override suspend fun onCreate() {}

    fun finishOnBoard() = launch {
        isOnBoardFinish.content = onBoardUseCase.finishOnBoard()
    }

}