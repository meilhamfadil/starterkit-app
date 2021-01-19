package id.co.pqm.lib.mvvm

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import id.co.pqm.lib.data.model.UserProfileModel
import id.co.pqm.lib.mvvm.data.StringConst.DATABASE_NAME

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseModule {

    val preferences: SharedPreferences by lazy {
        application.invoke().getSharedPreferences(application.invoke().packageName, MODE_PRIVATE)
    }

    // Application
    lateinit var application: () -> Context

    // Constant
    lateinit var baseUrlCaptor: () -> String
    lateinit var databaseNameCaptor: () -> String

    // Shared Preference
    lateinit var tokenCaptor: () -> String?
    lateinit var isOnBoardCaptor: () -> Boolean
    lateinit var userProfileCaptor: () -> UserProfileModel?

    val token: String?
        get() =
            if (::tokenCaptor.isInitialized) tokenCaptor.invoke()
            else null

    val baseUrl: String
        get() =
            if (::baseUrlCaptor.isInitialized) baseUrlCaptor.invoke()
            else ""

    val isOnBoard: Boolean
        get() =
            if (::isOnBoardCaptor.isInitialized) isOnBoardCaptor.invoke()
            else false

    val userProfile: UserProfileModel?
        get() =
            if (::userProfileCaptor.isInitialized) userProfileCaptor.invoke()
            else null

    val databaseName: String
        get() =
            if (::databaseNameCaptor.isInitialized) databaseNameCaptor.invoke()
            else DATABASE_NAME
}