package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by oohyugi on 4/5/18.
 */
data class LoginResponseMdl(@SerializedName("refresh_expired_at")
                            val refreshExpiredAt: String? = "",
                            @SerializedName("expired_at")
                            val expiredAt: String? = "",
                            @SerializedName("user")
                            val user: String?,
                            @SerializedName("token")
                            val token: String? = ""): Serializable

data class LoginRequestMdl(@SerializedName("phone")
                           var phone: String? = null,
                           @SerializedName("password")
                           var password: String? = null,
                           @SerializedName("provider_id")
                           var providerID: String?=null,
                           @SerializedName("provider")
                           var provider: String? = null,
                           @SerializedName("fcm_token")
                           var fcm_token: String? = null)
