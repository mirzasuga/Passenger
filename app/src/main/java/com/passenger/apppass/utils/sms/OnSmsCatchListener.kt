package com.stickearn.stickpass.utils.sms

/**
 * Created by oohyugi on 3/27/18.
 */
interface OnSmsCatchListener<T> {
    fun onSmsCatch(message:String)
}