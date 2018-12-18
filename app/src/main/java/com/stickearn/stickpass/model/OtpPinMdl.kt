package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName


data class OtpPinResponseMdl(@SerializedName("pin")
                             val pin: String = "",
                             @SerializedName("phone")
                             val phone: String = "",
                             @SerializedName("email")
                             val email: String = "")

data class OtpPinRequestMdl(@SerializedName("pin")
                             var pin: String? = "",
                             @SerializedName("phone")
                             var phone: String? = "",
                             @SerializedName("email")
                             var email: String? = "")

data class ResendOtpPinRequestMdl(
                            @SerializedName("phone")
                            var phone: String? = "")
data class ResendOtpPinResponseMdl(
        @SerializedName("pin")
        var pin: String? = "")