package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PointResponseMdl(@SerializedName("user_uuid")
                            val userUuid: String = "",
                            @SerializedName("account_type")
                            val accountType: String = "",
                            @SerializedName("balance")
                            val balance: Int = 0,
                            @SerializedName("updated_at")
                            val updatedAt: String = "",
                            @SerializedName("user_id")
                            val userId: Int = 0,
                            @SerializedName("created_at")
                            val createdAt: String = "",
                            @SerializedName("id")
                            val id: Int = 0,
                            @SerializedName("uuid")
                            val uuid: String = "",
                            @SerializedName("status")
                            val status: Int = 0)