package id.kudzoza.feature.home.data.repository

import id.kudzoza.lib.data.model.Payload

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

interface HomeRepository {

    suspend fun getUsername(): Payload<String>

}