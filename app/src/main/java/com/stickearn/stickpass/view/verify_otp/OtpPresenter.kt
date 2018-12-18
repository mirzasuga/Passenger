package com.stickearn.stickpass.view.verify_otp

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.otp.OtpRepository
import com.stickearn.stickpass.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/22/18.
 */
class OtpPresenter(mView: OtpView) : BasePresenter<OtpView>(mView) {

    val mRequest=OtpRepository()
    fun validateOtp(data:OtpPinRequestMdl){
        mView.showLoading()
        mRequest.validateOtp(data).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<OtpPinResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<OtpPinResponseMdl>) {
                        mView.stopLoading()
                        if (result.status){
                            mView.onSuccessValidate(result.data)
                        }else{
                            mView.errorLoading(result.message)
                        }

                    }

                })
    }

    fun resendOtpCode(data:ResendOtpPinRequestMdl){
        mView.showLoading()
        mRequest.resendOtpCode(data).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<ResendOtpPinResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<ResendOtpPinResponseMdl>) {
                        mView.stopLoading()
                        if (result.status){
                            mView.onSuccessNewCode(result.data)
                        }else{
                            mView.errorLoading(result.message)
                        }

                    }

                })
    }
}