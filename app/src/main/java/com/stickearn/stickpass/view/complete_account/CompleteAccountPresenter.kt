package com.stickearn.stickpass.view.complete_account

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.register_repo.RegisterRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/8/18.
 */
class CompleteAccountPresenter(mView: CompleteAccountView) : BasePresenter<CompleteAccountView>(mView) {


    var registerRequest= RegisterRepository()
    fun postRegister(registerRequestMdl: RegisterRequestMdl){
        mView.showLoading()
        registerRequest.postRegister(registerRequestMdl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<LoginResponseMdl>>(){
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
}