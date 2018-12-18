package com.stickearn.stickpass.helper

import android.app.AlertDialog
import android.content.Context
import java.util.regex.Pattern
import android.text.TextUtils
import android.widget.EditText
import com.stickearn.stickpass.R
import android.content.DialogInterface
import android.util.Base64
import com.google.gson.Gson
import com.stickearn.stickpass.view.main.MainActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stickearn.stickpass.model.AccountMdl
import com.stickearn.stickpass.model.DataOrderDetailMdl
import com.stickearn.stickpass.model.MartMdl
import com.stickearn.stickpass.model.UserMdl
import java.io.UnsupportedEncodingException
import java.util.*
import java.util.stream.Collectors


/**
 * Created by oohyugi on 1/17/18.
 */
class StringHelper {


    companion object {

        fun checkETStatus(context: Context, editText: EditText): Boolean {
            val strUserName = editText.text.toString()

            if (TextUtils.isEmpty(strUserName)) {
                editText.error = context.getResources().getString(R.string.cannot_be_empty)
                return false
            }
            return true
        }

        fun indonesiaFormat(angka: Double): String {

            return String.format("Rp.%,.0f", angka).replace(",".toRegex(), ".")
        }

        fun isEmailValid(email: String): Boolean {
            return Pattern.compile(
                    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(email).matches()
        }

        fun getListFromJsonString(json: String): MutableList<MartMdl> {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val typeOfList = object : TypeToken<MutableList<MartMdl>>() {

            }.type
            return gson.fromJson(json, typeOfList)

        }

        fun getListFromJsonOrderDetail(json: String): MutableList<DataOrderDetailMdl> {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val typeOfList = object : TypeToken<MutableList<DataOrderDetailMdl>>() {

            }.type
            return gson.fromJson(json, typeOfList)

        }


        fun convertStringUserToJson(json: String): AccountMdl? {

            return Gson().fromJson<AccountMdl>(json, AccountMdl::class.java)

        }

        /**
         * @param message the encoded message
         *
         * @return the decoded message
         */
        fun fromBase64(message: String): String? {
            val data = Base64.decode(message, Base64.DEFAULT)
            try {
                return String(data)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

            return null
        }

        fun toBase64(message: String): String? {
            val data: ByteArray
            try {
                data = message.toByteArray(charset("UTF-8"))
                return Base64.encodeToString(data, Base64.DEFAULT)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }

            return null
        }



        fun getRandomString(char:String): String {
            val chars = char.toCharArray()
            val stringBuilder = StringBuilder()
            val random = Random()
            var output: String
            for (lenght in 0..23) {
                val character = chars[random.nextInt(chars.size)]
                stringBuilder.append(character)
            }
            output = stringBuilder.toString()
            stringBuilder.delete(0, 24)

            return output
        }










    }


}