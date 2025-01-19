package com.example.personalcalculatingapplication

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.personalcalculatingapplication.sql.G

class PreferenceHelper(val context: Context) {

    object PreferenceHelper {

        fun customPrefs(context: Context, name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }


    private var session: SharedPreferences? = null

    init {
        session = PreferenceHelper.customPrefs(context, G.PREFERENCE_NAME)
    }

    fun setUserName(userName: String) {
        session!!.edit().putString(G.USERNAME, userName).apply()
    }

    fun getUserName(): String = session!!.getString(G.USERNAME, "") ?: "";

    fun exit() {
        session!!.edit().clear().apply()
    }
}