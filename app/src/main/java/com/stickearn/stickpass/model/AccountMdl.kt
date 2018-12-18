package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 3/9/18.
 */
class AccountMdl(
        @SerializedName("account")
        val account: UserMdl
)