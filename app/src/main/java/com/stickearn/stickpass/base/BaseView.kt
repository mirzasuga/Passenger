package com.stickearn.stickpass.base

/**
 * Created by oohyugi on 1/15/18.
 */
interface BaseView {

    fun showLoading()
    fun errorLoading(errorMessage:String?)
    fun stopLoading()
}