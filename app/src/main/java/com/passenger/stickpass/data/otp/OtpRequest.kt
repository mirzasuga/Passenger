package com.stickearn.stickpass.data.otp

import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/22/18.
 */
interface OtpRequest {

    fun validateOtp(data:OtpPinRequestMdl):Observable<BaseMdl<OtpPinResponseMdl>>
    fun resendOtpCode(data:ResendOtpPinRequestMdl):Observable<BaseMdl<ResendOtpPinResponseMdl>>
}