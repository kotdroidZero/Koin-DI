package com.app.koincrsample.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.app.koincrsample.data.local.prefs.SharedPreferencesKeys.Companion.ACCESS_TOKEN
import com.app.koincrsample.data.local.prefs.SharedPreferencesKeys.Companion.PRIVATE_MODE
import com.app.koincrsample.data.local.prefs.SharedPreferencesKeys.Companion.SHAREPRE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @AUTHOR Pushkar Srivastava
 * @date 08/02/2020
 */


/**
 *  single definition provides one instance of this class,means Singleton class
 */
val prefModule = module {
    single { PrefManager.getInstance().initPref(androidContext()) }
}


class PrefManager
private constructor() : SharedPreferencesKeys {
    var pref: SharedPreferences? = null

    var accessToken: String
        get() = pref!!.getString(ACCESS_TOKEN, "")!!
        set(accessToken) {
            pref!!.edit().putString(ACCESS_TOKEN, accessToken).apply()
        }


    /**
     * init shared preference
     * @param context application context
     */

    fun initPref(context: Context): PrefManager {
        pref = context.getSharedPreferences(SHAREPRE_NAME, PRIVATE_MODE)
        return this
    }

    fun clearPrefData() {
        pref!!.edit()/*.remove(USER_PROFILE)*/
            .remove(ACCESS_TOKEN)
            .apply()
    }

    companion object {
        private var instance: PrefManager? = null

        /**
         * Create shared preference class instance
         *
         * @return instance
         */
        fun getInstance(): PrefManager {
            if (instance == null) {
                instance = PrefManager()
            }
            return instance!!
        }
    }

}