package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by oohyugi on 4/5/18.
 */
data class RegisterRequestMdl(@SerializedName("password")
                              val password: String? = null,
                              @SerializedName("password_confirmation")
                              val passwordConfirmation: String? = null,
                              @SerializedName("phone")
                              val phone: String? = null,
                              @SerializedName("name")
                              val name: String? = null,
                              @SerializedName("email")
                              val email: String? = null,
                              @SerializedName("group")
                              val group: MutableList<Int> = mutableListOf(3,4,6),
                              @SerializedName("token")
                              val token: String? = null,
                              @SerializedName("provider")
                              val provider:String?=null,
                              @SerializedName("provider_id")
                              val provider_id: String? =null,
                              @SerializedName("fcm_token")
                              val fcm_token: String? =null

): Serializable

data class RegisterResponseMdl(@SerializedName("refresh_expired_at")
                               val refreshExpiredAt: String? = "",
                               @SerializedName("expired_at")
                               val expiredAt: String? = "",
                               @SerializedName("user")
                               val user: String?=null,
                               @SerializedName("token")
                               val token: String? = ""):Serializable