package id.co.feature.home.data.usecase

import id.co.lib.data.service.Completable
import id.co.lib.data.usecase.UseCase

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

interface HomeUseCase : UseCase {

    suspend fun getUsername(): Completable<String>

}