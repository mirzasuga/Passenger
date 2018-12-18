package com.stickearn.stickpass.model

import com.google.gson.annotations.SerializedName

data class DataMdl<T>(@SerializedName("data")
                                       val data: T)