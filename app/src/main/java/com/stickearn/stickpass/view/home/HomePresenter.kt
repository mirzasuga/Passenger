package com.stickearn.stickpass.view.home

import android.util.Log
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.login_repo.LoginRepository
import com.stickearn.stickpass.data.login_repo.LoginRequest
import com.stickearn.stickpass.data.main_repo.MainRepository
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by oohyugi on 1/15/18.
 */
class HomePresenter(mView: HomeView) : BasePresenter<HomeView>(mView) {

    var mMainRequest: MainRepository = MainRepository()
    val mRequestPoint = PointRepository()
    val mRequestLogin = LoginRepository()
    fun bannerLoadData() {
        mView.showLoading()

        mMainRequest.loadBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<BannerMdl>>() {
                    override fun onResponseError(s: String) {
                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: List<BannerMdl>) {
                        mView.stopLoading()
                        mView.displayListBanner(t)
                    }
                })
    }

    /**
     * get data from repo
     *
     */

    fun menuLoadData() {
        mMainRequest.loadMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<MenuMdl>>() {
                    override fun onResponseError(s: String) {

                    }

                    override fun onResponseSuccess(t: List<MenuMdl>) {
                        mView.displayMenuData(t)
                    }
                })
    }

    fun postAuth(email:String,password:String){

        val loginRequestMdl = LoginRequestMdl(phone = email,password = password)
        mRequestLogin.postAuth(loginRequestMdl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<LoginResponseMdl>>(){
                    override fun onResponseError(s: String) {

//                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<LoginResponseMdl>) {

                        mView.onAuthSuccess(t.data)
                    }

                })
    }
    private var subscription: Disposable? = null
    fun getMyPoint(auth: String,token: String){
        mView.showLoadingPoint()
       mRequestPoint.getPoint(auth,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result->

                    mView.stopLoadingPoint()
                    mView.onSuccessLoadMyPoint(result)

                },{
                    error->
                    mView.stopLoadingPoint()
                    mView.errorLoadingPoint(error.message.toString())
                })
    }



}