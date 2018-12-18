package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 1/16/18.
 */
data class BankMdl (
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("bank_name")
        var name: String? = null
)