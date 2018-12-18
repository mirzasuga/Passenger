package com.stickearn.stickpass.services

import android.util.Log.wtf
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.stickearn.stickpass.helper.PrefHelper

/**
 * Created by oohyugi on 2/6/18.
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val token = FirebaseInstanceId.getInstance().token
        wtf("fcm token",token)
        PrefHelper.saveFcmToken(applicationContext,token.toString())
    }
}