package id.co.feature.home.screen.home

import id.co.feature.home.data.usecase.HomeUseCase
import id.co.feature.home.data.usecase.HomeUseCaseImpl
import id.co.lib.mvvm.BaseVM
import id.co.lib.mvvm.data.Mutable
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
        homeUseCase.getUsername().execute(
            onSuccess = { user.content = it.orEmpty() },
            onFailed = { message -> showToast(message) }
        )
        isLoading.content = false
    }
}