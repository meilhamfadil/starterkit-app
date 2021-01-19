package id.co.pqm.feature.home.data.repository

import id.co.pqm.lib.data.model.Payload

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

interface HomeRepository {

    suspend fun getUsername(): Payload<String>

}