package com.stickearn.stickpass.view.splash_screen

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.login_repo.LoginRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginRequestMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/21/18.
 */
class SplashPresenter(mView: SplashView) : BasePresenter<SplashView>(mView) {

    val mRequestLogin = LoginRepository()
    fun postAuth(data: LoginRequestMdl){


        mRequestLogin.postAuth(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {

//                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {

                        mView.onAuthSuccess(t.data)
                    }

                })
    }
}