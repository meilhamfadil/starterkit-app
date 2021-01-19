package id.co.lib.mvvm

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import id.co.lib.mvvm.data.StringConst.KEY_TOKEN
import id.co.lib.mvvm.data.StringConst.KEY_USER_DATA
import java.lang.Exception

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseApplication : Application() {

    private val pref by lazy {
        applicationContext.getSharedPreferences(
            BuildConfig.LIBRARY_PACKAGE_NAME,
            Context.MODE_PRIVATE
        )
    }

    abstract fun appendModule(): List<BaseModule>

    override fun onCreate() {
        super.onCreate()
        appendModule().forEach {
            it.application = { this@BaseApplication }
            it.tokenCaptor = { pref.getString(KEY_TOKEN, "") }
            it.userDataCaptor = {
                try {
                    Gson().fromJson(pref.getString(KEY_USER_DATA, null), Any::class.java)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    fun login(token: String, userData: String) {
        pref.edit().apply() {
            putString(KEY_USER_DATA, userData)
            putString(KEY_TOKEN, token)
        }.apply()
    }

    fun logout() {
        pref.edit().apply {
            remove(KEY_USER_DATA)
            remove(KEY_TOKEN)
        }.apply()
    }
}