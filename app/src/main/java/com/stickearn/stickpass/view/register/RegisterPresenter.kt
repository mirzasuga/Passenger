package com.stickearn.stickpass.view.register

import android.text.TextUtils
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.register_repo.RegisterRepository
import com.stickearn.stickpass.helper.StringHelper
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/9/18.
 */
class RegisterPresenter(mView: RegisterView) : BasePresenter<RegisterView>(mView) {

    /**
     * validate register
     */
    var registerRequest=RegisterRepository()
    fun postRegister(registerRequestMdl: RegisterRequestMdl){
        mView.showLoading()
        registerRequest.postRegister(registerRequestMdl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {

                        mView.stopLoading()
                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {
                        mView.stopLoading()
                        mView.onRegisterSuccess(t,registerRequestMdl)
                    }

                })
    }
    fun validateRegister(mName: String, mEmail: String, mPhoneNumber: String, mPass: String, mConfirmPass: String): Boolean {
        var isValidate:Boolean =true
        if (!StringHelper.isEmailValid(mEmail)){
            isValidate=false
//            mView.errorValidate("Format email salah")
        }
        if (mPhoneNumber.length<8){
            isValidate=false
//            mView.errorValidate("Phone to sort")
        }
        if (TextUtils.isEmpty(mName)){

        }

        return isValidate
    }

    fun postCheckEmailSosmed(mUser: RegisterRequestMdl, account: String?)
    {
        mView.showLoading()
        registerRequest.postRegister(mUser)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {

                        mView.stopLoading()
                        mView.errorLoadingSosmed(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {
                        mView.stopLoading()
                        mView.onCheckemailSosmedSuccess(t, account!!)
                    }

                })
    }
}