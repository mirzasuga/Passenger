package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class NotificationResponseMdl(
                                val read: Int = 0,
                                @SerializedName("subject")
                                val subject: String = "",
                                @SerializedName("id")
                                val id: Int = 0,
                                @SerializedName("to")
                                val to: String = "",
                                @SerializedName("body")
                                val body: String = "",
                                @SerializedName("uuid")
                                val uuid: String = "",
                                @SerializedName("created_at")
                                val createdAt: String? =null
)