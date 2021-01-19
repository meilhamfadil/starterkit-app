package id.co.pqm.lib.mvvm

import android.app.Application
import com.google.gson.Gson
import id.co.pqm.lib.data.model.UserProfileModel
import id.co.pqm.lib.mvvm.data.StringConst.DATABASE_NAME
import id.co.pqm.lib.mvvm.data.StringConst.KEY_ON_BOARDING
import id.co.pqm.lib.mvvm.data.StringConst.KEY_TOKEN
import id.co.pqm.lib.mvvm.data.StringConst.KEY_USER_DATA

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseApplication : Application() {

    protected abstract val listModule: List<BaseModule>

    override fun onCreate() {
        super.onCreate()
        listModule.forEach {
            // Application
            it.application = { this@BaseApplication }

            // Constant
            it.databaseNameCaptor = { DATABASE_NAME }

            // Shared Preferences
            it.tokenCaptor = { it.preferences.getString(KEY_TOKEN, null) }
            it.isOnBoardCaptor = { it.preferences.getString(KEY_ON_BOARDING, null).isNullOrEmpty() }
            it.userProfileCaptor = {
                it.preferences.getString(KEY_USER_DATA, null)?.let { userData ->
                    Gson().fromJson(userData, UserProfileModel::class.java)
                }
            }
        }
    }
}