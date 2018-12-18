package com.stickearn.stickpass.utils.sms

import android.Manifest
import android.app.Activity
import android.support.v4.app.Fragment
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.os.Build
import java.util.regex.Pattern


/**
 * Created by oohyugi on 3/27/18.
 */
class SmsVerify(activity: Activity, onSmsCatchListener: OnSmsCatchListener<String>) {
    private val PERMISSION_REQUEST_CODE = 12
    var activity: Activity
   var onSmsCatchListener: OnSmsCatchListener<String>
    var smsReceiver: SmsReceiver
    init {
        this.activity = activity
        smsReceiver = SmsReceiver()
        smsReceiver.setCallback(onSmsCatchListener)
        this.onSmsCatchListener = onSmsCatchListener
    }


    fun onStart() {
        if (isStoragePermissionGranted(activity)) {
            registerReceiver()
        }
    }

    fun registerReceiver() {
        smsReceiver = SmsReceiver()
        smsReceiver.setCallback(onSmsCatchListener)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")
        activity.registerReceiver(smsReceiver, intentFilter)
    }

//    fun setPhoneNumberFilter(phoneNumber: String) {
//        this.phoneNumber = phoneNumber
//    }

    fun onStop() {
        try {
            activity.unregisterReceiver(smsReceiver)
        } catch (ignore: IllegalArgumentException) {
            //receiver not registered
        }

    }

//    fun settFilter(regexp: String) {
//        this.filter = regexp
//    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                registerReceiver()
            }
        }
    }

    fun isStoragePermissionGranted(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                if (activity == null) {
                    ActivityCompat.requestPermissions(activity,
                            arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS), PERMISSION_REQUEST_CODE)
                } else {
                    activity.requestPermissions(
                            arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS), PERMISSION_REQUEST_CODE)
                }
                return false
            }
        } else {
            return true
        }
    }


    fun parseCode(message: String): String {
        val p = Pattern.compile("\\b\\d{4}\\b")
        val m = p.matcher(message)
        var code = ""
        while (m.find()) {
            code = m.group(0)
        }
        return code
    }

}