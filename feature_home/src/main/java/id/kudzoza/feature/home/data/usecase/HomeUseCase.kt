package id.kudzoza.feature.home.data.usecase

import id.kudzoza.lib.data.service.Completable
import id.kudzoza.lib.data.usecase.UseCase

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

interface HomeUseCase : UseCase {

    suspend fun getUsername(): Completable<String>

}