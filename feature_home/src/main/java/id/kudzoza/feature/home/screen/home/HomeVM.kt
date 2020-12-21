package id.kudzoza.feature.home.screen.home

import id.kudzoza.feature.home.data.usecase.HomeUseCase
import id.kudzoza.feature.home.data.usecase.HomeUseCaseImpl
import id.kudzoza.lib.mvvm.BaseVM
import id.kudzoza.lib.mvvm.data.Mutable
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