package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class PayloadRequestBaseMdl<T>(@SerializedName("payload")
                       val payload: T)