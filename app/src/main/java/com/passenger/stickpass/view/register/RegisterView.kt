package com.stickearn.stickpass.view.register

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl

/**
 * Created by oohyugi on 2/9/18.
 */
interface RegisterView:BaseView {
    fun onRegisterSuccess(data: BaseMdl<LoginResponseMdl>, mConfirmPass: RegisterRequestMdl)
    fun errorLoadingSosmed(s: String)
    fun onCheckemailSosmedSuccess(t: BaseMdl<LoginResponseMdl>, account: String)
}