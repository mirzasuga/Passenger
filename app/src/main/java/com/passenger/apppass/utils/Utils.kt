package com.stickearn.stickpass.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.util.Log.wtf
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.stickearn.stickpass.R
import com.stickearn.stickpass.helper.PrefHelper
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.AccountMdl
import com.stickearn.stickpass.model.MartMdl
import com.stickearn.stickpass.view.main.MainActivity


/**
 * Created by oohyugi on 1/16/18.
 */
/**
 * A thread unsafe lazy function.
 * This function 'must' be called only on single thread.
 */
class Utils {
//    fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)


    companion object {

        fun showSnackBar(view: View, message: String, isRetry: Boolean) {
            if (isRetry) {
                Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Retry") {

                }.show()
            } else {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
            }

        }

        fun showErrorAlert(context: Context, message: String) {
            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(R.string.alert_title)
                    .setMessage(message)
                    .setPositiveButton(R.string.close, DialogInterface.OnClickListener { dialog, which ->
                        if (message.contains("404")) {
                            logout(context)
                        } else {
                            dialog.dismiss()
                        }
                    })
                    .create()
            alertDialog.show()

            //set dialog button color
            //get color
            val buttonColor = context.getResources().getColor(R.color.colorAccent)
            //get the positive button
            val pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            //set the color to the buttton
            pbutton.setTextColor(buttonColor)
        }

        fun logout(context: Context) {
            PrefHelper.saveLoggedUser(context, null)
            val activity = context as MainActivity?
            activity!!.gotoHome()
        }


        fun getUserMdl(context: Context): AccountMdl {
            val data64 = StringHelper.fromBase64(PrefHelper.getAuth(context)!!.user!!)
            wtf("dataUser", data64)
            return StringHelper.convertStringUserToJson(data64!!)!!
        }

        fun convertUser(context: Context, auth: String): AccountMdl {
            val data64 = StringHelper.fromBase64(auth)
            wtf("dataUser", data64)
            return StringHelper.convertStringUserToJson(data64!!)!!
        }

        fun getAuth(context: Context): String {
            return PrefHelper.getAuth(context)!!.user!!
        }

        fun getToken(context: Context): String {
            return "Bearer ${PrefHelper.getAuth(context)!!.token!!}"
        }

//        fun getuidPoint(context: Context): String {
//
////                return Utils.getUserMdl(context).account.uuidPoint!!
//
//
//        }

        fun getUuidUser(context: Context): String {
            return Utils.getUserMdl(context).account.uuid!!


        }

        fun setClipboard(context: Context, text: String) {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
                clipboard.text = text
            } else {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("Copied Text", text)
                clipboard.primaryClip = clip
            }
            Toast.makeText(context, "Your voucher code has been copied", Toast.LENGTH_LONG).show()
        }


        fun <T>convertListFromJsonString(json: String): List<T> {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            val typeOfList = object : TypeToken<MutableList<T>>() {

            }.type
            return gson.fromJson(json, typeOfList)

        }


        fun convertModelFromJsonString(json: String): Any? {

            return Gson().fromJson<AccountMdl>(json, Any::class.java)

        }

    }


}
