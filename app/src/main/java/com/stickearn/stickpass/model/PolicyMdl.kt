package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PolicyMdl(
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("body")
                     val body: String = "",
                     @SerializedName("uuid")
                     val uuid: String = "",
                     @SerializedName("status")
                     val status: Int = 0)