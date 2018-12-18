package com.stickearn.stickpass.view.verify_otp

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.OtpPinResponseMdl
import com.stickearn.stickpass.model.ResendOtpPinResponseMdl

/**
 * Created by oohyugi on 3/22/18.
 */
interface OtpView:BaseView {
    fun onSuccessValidate(data: OtpPinResponseMdl)
    fun onSuccessNewCode(data: ResendOtpPinResponseMdl)
}