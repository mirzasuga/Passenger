package com.stickearn.stickpass.view.account

import android.util.Log
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.data.profile_repo.ProfileRepository
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.ProfileResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/14/18.
 */
class AccountPresenter(mView: AccountView) : BasePresenter<AccountView>(mView) {


    var mRequestPoint = PointRepository()
    var mRequestProfile = ProfileRepository()
    fun getMyPoint(auth: String,token: String,uuidPoint:String){
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



    fun getProfile(auth: String,token: String){
        mView.showLoading()
        mRequestProfile.loadProfile(auth,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<ProfileResponseMdl>(){
                    override fun onResponseError(errorMessage: String) {
                        Log.wtf("onError profile", errorMessage)
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: ProfileResponseMdl) {
                        mView.stopLoading()
                        mView.onSuccessLoadProfile(result)
                    }

                })
    }
}