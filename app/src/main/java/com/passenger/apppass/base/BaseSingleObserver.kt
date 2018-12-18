package com.stickearn.stickpass.base


import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.TimeoutException
import retrofit2.adapter.rxjava2.Result.response
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.HttpException
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.adapter.rxjava2.Result.response
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.adapter.rxjava2.Result.response
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.stickearn.stickpass.model.ErrorResponseMdl
import android.R.attr.data
import org.json.JSONObject
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import io.reactivex.Single
import io.reactivex.SingleObserver
import java.net.ConnectException
import java.net.SocketTimeoutException


/**
 * Created by oohyugi on 1/16/18.
 */
abstract class BaseSingleObserver<T> : SingleObserver<T> {


    override fun onSuccess(t: T){
        onResponseSuccess(t)
    }



    override fun onSubscribe(d: Disposable) {
        DisposableManager().add(d)
    }


    override fun onError(e: Throwable) {
//        onComplete()
//        DisposableManager().dispose()
        if (e is HttpException) {
            val exeptions = e
            if (!e.message!!.contains("Internal Server")){
                val errorBody = exeptions.response().errorBody()!!.string()
                val jObjError = JSONObject(errorBody)

                try {
                    onResponseError(jObjError.getString("message"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }else{
                onResponseError(e.message.toString())
            }
        } else if (e is SocketTimeoutException) {
            onResponseError("Request time out")
        } else if (e is ConnectException) {
            if (e.message != null) {
                if (e.message.toString().contains("Failed to ") || e.message.toString().contains("Unable to resolve host")) {
                    onResponseError("Please Check Your Connection")
                } else if (e.message.toString().contains("HTTP")) {
                    val message = e.message.toString().substring(9)
                    onResponseError(message)
                }
            }

        }else{
            onResponseError(e.message.toString())
        }



    }

    abstract fun onResponseError(s: String)
    abstract fun onResponseSuccess(t: T)



}