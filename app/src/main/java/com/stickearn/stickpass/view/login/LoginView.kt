package com.stickearn.stickpass.view.login

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface LoginView:BaseView {
    fun onAuthSuccess(data: LoginResponseMdl, remember: LoginRequestMdl)
    fun errorLoadingSosmed(erorrMessage: String, mUser: RegisterRequestMdl)
    fun onAuthSuccessSosmed(data: LoginResponseMdl, providerID: LoginRequestMdl)

}