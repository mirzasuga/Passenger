package com.stickearn.stickpass.data.otp

import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.helper.Constant
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 3/22/18.
 */
class OtpRepository :BaseRepository(),OtpRequest {
    override fun resendOtpCode(data: ResendOtpPinRequestMdl): Observable<BaseMdl<ResendOtpPinResponseMdl>> {
        return mApi.getApiServices().postResendOtpPin(data).flatMap {
            Observable.just(it)
        }
    }

    val mApi = ApiClient(Constant.BASE_URL_AUTH)
    override fun validateOtp(data: OtpPinRequestMdl): Observable<BaseMdl<OtpPinResponseMdl>> {
        return mApi.getApiServices().postOtpPin(data).flatMap {
            Observable.just(it)
        }
    }
}