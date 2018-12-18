package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PurchasedRequestMdl(@SerializedName("user_uuid")
                               val userUuid: String = "",
                               @SerializedName("amount")
                               val amount: String = "",
                               @SerializedName("action")
                               val action: String = "",
                               @SerializedName("voucher_id")
                               val voucherId: String = "",
                               @SerializedName("source")
                               val source: String = "")