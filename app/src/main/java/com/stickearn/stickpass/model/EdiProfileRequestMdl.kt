package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class EdiProfileRequestMdl(@SerializedName("gender")
                                val gender: String = "-",
                                @SerializedName("phone")
                                val phone: String? = null,
                                @SerializedName("last_name")
                                val lastName: String? = null,
                                @SerializedName("uuid")
                                val uuid: String? = null,
                                @SerializedName("first_name")
                                val firstName: String? = null,
                                @SerializedName("email")
                                val email: String? = null)