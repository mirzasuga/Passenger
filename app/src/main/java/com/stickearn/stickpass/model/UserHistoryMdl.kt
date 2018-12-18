package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class UserHistoryResponseMdl(@SerializedName("country")
                          val country: CountryMdl,
                          @SerializedName("created_at")
                          val createdAt: String = "",
                          @SerializedName("_id")
                          val Id: String = "",
                          @SerializedName("eventMdl")
                          val eventMdl: EventMdl,
                          @SerializedName("uuid")
                          val uuid: String = "",
                          @SerializedName("deviceMdl")
                          val deviceMdl: DeviceMdl)


data class DeviceMdl(@SerializedName("name")
                     val name: String = "",
                     @SerializedName("token")
                     val token: String = "")

data class EventMdl(@SerializedName("activity")
                    val activity: String = "",
                    @SerializedName("ip")
                    val ip: String = "",
                    @SerializedName("channel")
                    val channel: String = "")


data class CountryMdl(@SerializedName("code")
                      val code: String = "",
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("currency_code")
                      val currencyCode: String = "")