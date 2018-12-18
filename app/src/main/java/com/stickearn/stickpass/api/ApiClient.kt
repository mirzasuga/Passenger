package com.stickearn.stickpass.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by oohyugi on 1/15/18.
 */
class ApiClient(val baseURL: String) {

    lateinit var mApiServices: ApiServices


    init {
        val retrofit = initRetrofit(baseURL)
        initServices(retrofit)
    }

    private fun initServices(retrofit: Retrofit) {
        mApiServices = retrofit.create(ApiServices::class.java)
    }

    fun getApiServices(): ApiServices {
        return mApiServices
    }

    private fun initRetrofit(baseURL: String): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build()
                chain.proceed(request)
            })
            addInterceptor(interceptor)
        }
        return Retrofit.Builder().baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
    }
}

