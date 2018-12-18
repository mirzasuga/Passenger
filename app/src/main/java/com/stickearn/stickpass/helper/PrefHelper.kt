package com.stickearn.stickpass.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.UserMdl

/**
 * Created by oohyugi on 1/16/18.
 */
class PrefHelper {

    private val mGson: Gson? = null
    private val mPreferences: SharedPreferences? = null




    fun logoutUser() {
        mPreferences!!.edit().clear().apply()

    }



    companion object {
        private val PREF_USER = "PREF_USER"
        private val PREF_TOKEN = "PREF_TOKEN"
        private val PREF_TOKEN_FCM = "PREF_TOKEN_FCM"
        private val PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL"
        private val PREF_AUTH = "PREF_AUTH"
        private val PREF_REMEMBER = "PREF_REMEMBER"
        private val PREF_NAME = "PREF_NAME"


        private fun getSP(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun savePref(context: Context, key: String, value: String) {
            getSP(context).edit().putString(key, value).commit()
        }

        fun getPref(context: Context, key: String): String? {
            return getSP(context).getString(key, null)
        }



        fun saveLoggedUser(mContex: Context, mdao: UserMdl?) {
            val pref = PreferenceManager
                    .getDefaultSharedPreferences(mContex)
            pref.edit().putString(PREF_USER, Gson().toJson(mdao)).apply()

        }

        fun getLoggedUser(mContext: Context): UserMdl? {
            return Gson().fromJson<UserMdl>(getPref(mContext, PREF_USER), UserMdl::class.java)


        }

        fun saveAuth(mContex: Context, auth: LoginResponseMdl?) {
            val pref = PreferenceManager.getDefaultSharedPreferences(mContex)
            pref.edit().putString(PREF_AUTH, Gson().toJson(auth)).apply()

        }

        fun getAuth(mContext: Context): LoginResponseMdl? {
            return Gson().fromJson<LoginResponseMdl>(getPref(mContext, PREF_AUTH), LoginResponseMdl::class.java)


        }

        fun saveFcmToken(mContex: Context, token:String) {
            val pref = PreferenceManager.getDefaultSharedPreferences(mContex)
            pref.edit().putString(PREF_TOKEN_FCM, token).apply()

        }

        fun getFcmToken(mContext: Context): String? {
            return getPref(mContext, PREF_TOKEN_FCM)


        }

        fun saveRemember(mContex: Context, data:LoginRequestMdl) {
            val pref = PreferenceManager
                    .getDefaultSharedPreferences(mContex)
            pref.edit().putString(PREF_REMEMBER, Gson().toJson(data)).apply()

        }

        fun getRemember(mContext: Context): LoginRequestMdl? {

            return Gson().fromJson<LoginRequestMdl>(getPref(mContext, PREF_REMEMBER), LoginRequestMdl::class.java)
        }

        fun saveName(mContex: Context, name:String) {
            val pref = PreferenceManager
                    .getDefaultSharedPreferences(mContex)
            pref.edit().putString(PREF_NAME, name).apply()
        }

        fun getName(mContext: Context): String? {
            return getPref(mContext, PREF_NAME)
        }

        fun saveFirstInstall(mContex: Context, isFirst:Boolean) {
            val pref = PreferenceManager
                    .getDefaultSharedPreferences(mContex)
            pref.edit().putBoolean(PREF_FIRST_INSTALL, isFirst).apply()

        }

        fun getIsFirstInstall(mContext: Context): Boolean? {
            return getSP(mContext).getBoolean(PREF_FIRST_INSTALL, true)
        }
    }

}
