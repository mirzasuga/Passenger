package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class AccessItemMdl(@SerializedName("uuid_mart")
                      val uuidMart: String = "",
                         @SerializedName("group")
                      val group: String = "")