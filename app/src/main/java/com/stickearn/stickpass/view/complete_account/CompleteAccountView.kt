package com.stickearn.stickpass.view.complete_account

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl

/**
 * Created by oohyugi on 3/8/18.
 */
interface CompleteAccountView: BaseView {
    fun onRegisterSuccess(t: BaseMdl<LoginResponseMdl>, registerRequestMdl: RegisterRequestMdl)
}