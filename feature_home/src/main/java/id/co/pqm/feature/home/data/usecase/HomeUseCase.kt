package id.co.pqm.feature.home.data.usecase

import id.co.pqm.lib.data.model.Payload


/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

interface HomeUseCase {

    suspend fun getUsername(): Payload<String>

}