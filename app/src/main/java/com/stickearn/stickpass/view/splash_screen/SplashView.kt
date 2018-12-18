package com.stickearn.stickpass.view.splash_screen

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.LoginResponseMdl

/**
 * Created by oohyugi on 3/21/18.
 */
interface SplashView:BaseView {
    fun onAuthSuccess(data: LoginResponseMdl)
}