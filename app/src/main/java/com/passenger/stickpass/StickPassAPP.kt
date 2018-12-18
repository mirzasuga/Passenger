package com.stickearn.stickpass

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.helper.StringHelper
import io.fabric.sdk.android.Fabric

/**
 * Created by oohyugi on 1/31/18.
 */
class StickPassAPP: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext!!)
//        Fabric.with(this, Crashlytics())
        AppEventsLogger.activateApp(this)


    }

}