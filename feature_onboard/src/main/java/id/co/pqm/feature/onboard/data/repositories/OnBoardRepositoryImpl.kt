package id.co.pqm.feature.onboard.data.repositories

import android.content.SharedPreferences
import id.co.pqm.lib.data.model.Payload
import id.co.pqm.lib.module.OnBoardModule
import id.co.pqm.lib.mvvm.data.StringConst.KEY_ON_BOARDING
import java.lang.Exception

/**
 * Created by CHPN-RND
 * on 19/01/2021
 **/
class OnBoardRepositoryImpl(
    private val module: OnBoardModule = OnBoardModule.get(),
    private val pref: SharedPreferences = module.preferences
) : OnBoardRepository {

    override fun isOnBoardAvailable(): Payload<Boolean> {
        return Payload(data = pref.getString(KEY_ON_BOARDING, "").isNullOrEmpty())
    }

    override fun finishOnBoard(): Payload<Boolean> {
        return try {
            pref.edit().apply {
                putString(KEY_ON_BOARDING, "OK")
            }.apply()
            Payload(data = true)
        } catch (e: Exception) {
            Payload(false, e.localizedMessage, false)
        }
    }

    companion object {
        private val INSTANCE = OnBoardRepositoryImpl()

        fun getInstance() = INSTANCE
    }

}