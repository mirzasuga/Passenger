package com.stickearn.stickpass.view.login

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.login_repo.LoginRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/8/18.
 */
class LoginPresenter(mView: LoginView) : BasePresenter<LoginView>(mView) {


    var mRequest= LoginRepository()
    fun postAuth(data:LoginRequestMdl){
        mView.showLoading()
        mRequest.postAuth(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {
                        mView.stopLoading()
                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {
                        mView.stopLoading()

                            mView.onAuthSuccess(t.data, data)


                    }

                })
    }

    fun postAuthSosmed(data: LoginRequestMdl, mUser: RegisterRequestMdl) {
        mView.showLoading()
        mRequest.postAuth(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {
                        mView.stopLoading()
                        mView.errorLoadingSosmed(s,mUser)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {
                        mView.stopLoading()
                        mView.onAuthSuccessSosmed(t.data, data)


                    }

                })
    }


}