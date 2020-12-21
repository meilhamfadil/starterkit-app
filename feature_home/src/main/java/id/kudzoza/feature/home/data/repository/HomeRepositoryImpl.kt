package id.kudzoza.feature.home.data.repository

import id.kudzoza.feature.home.data.service.HomeRaw
import id.kudzoza.lib.data.model.Payload
import java.util.*

/**
 * Created by Kudzoza
 * on 21/12/2020
 **/

class HomeRepositoryImpl : HomeRepository {

    override suspend fun getUsername(): Payload<String> {
        if (Random().nextBoolean())
            return Payload(false, "Username didn't Exist")

        return Payload(data = HomeRaw.username)
    }

    companion object {
        private val INSTANCE = HomeRepositoryImpl()
        fun getInstance() = INSTANCE
    }

}