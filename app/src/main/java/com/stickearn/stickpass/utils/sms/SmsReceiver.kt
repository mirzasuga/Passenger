package com.stickearn.stickpass.utils.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat.getExtras
import android.os.Bundle
import android.util.Log
import android.os.Build
import android.telephony.SmsMessage


/**
 * Created by oohyugi on 3/27/18.
 */
class SmsReceiver : BroadcastReceiver() {
    private lateinit var callback: OnSmsCatchListener<String>
//    lateinit var phoneNumberFilter: String
//    lateinit var filter: String

    /**
     * Set result callback
     * @param callback OnSmsCatchListener
     */
    fun setCallback(callback: OnSmsCatchListener<String>) {
        this.callback = callback
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent!!.getExtras()
        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                for (i in pdusObj.indices) {
                    val currentMessage = getIncomingMessage(pdusObj[i], bundle)

//                    if (phoneNumberFilter != null && phoneNumber != phoneNumberFilter) {
//                        return
//                    }
                        val message = currentMessage.getDisplayMessageBody()
//                    if (filter != null && !message.matches(filter.toRegex())) {
//                        return
//                    }

                    if (callback != null) {
                        callback.onSmsCatch(message)
                    }
                } // end for loop
            } // bundle is null

        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e)
        }


    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray)
        }
        return currentSMS
    }





}