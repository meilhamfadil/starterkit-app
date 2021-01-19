package id.co.pqm.feature.home.screen.home

import id.co.pqm.feature.home.data.usecase.HomeUseCase
import id.co.pqm.feature.home.data.usecase.HomeUseCaseImpl
import id.co.pqm.lib.mvvm.BaseVM
import id.co.pqm.lib.mvvm.data.Mutable
import kotlinx.coroutines.delay

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class HomeVM(
    private val homeUseCase: HomeUseCase = HomeUseCaseImpl.getInstance()
) : BaseVM() {

    val user = Mutable("")
    val isLoading = Mutable(false)

    override suspend fun onCreate() {
        initData()
    }

    private fun initData() = launch {
        isLoading.content = true
        delay(2000)
        homeUseCase.getUsername().let {
            if (it.isSuccess) user.content = it.data.orEmpty()
            else showToast(it.message)
        }
        isLoading.content = false
    }
}