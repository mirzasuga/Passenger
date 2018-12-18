package com.stickearn.stickpass.model

/**
 * Created by oohyugi on 1/16/18.
 */
data class BaseMdl<T> (
        var status:Boolean,
        var message:String?,
        var data:T,
        var meta: MetaMdl
)