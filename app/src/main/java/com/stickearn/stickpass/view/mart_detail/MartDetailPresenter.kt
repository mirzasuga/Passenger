package com.stickearn.stickpass.view.mart_detail

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.login_repo.LoginRequest
import com.stickearn.stickpass.data.mart_repo.MartRepository
import com.stickearn.stickpass.model.MartResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 1/29/18.
 */
class MartDetailPresenter(mView: MartDetailView) : BasePresenter<MartDetailView>(mView) {

    var mRequest = MartRepository()
    fun getMartDetail(boxId:String,productId:String,auth:String,token:String){
        mView.showLoading()
        mRequest.loadMartDetail(boxId,productId,auth,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<MartResponseMdl>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: MartResponseMdl) {
                        mView.stopLoading()
                        mView.onSuccessLoadDetailMart(result)
                    }

                })
    }
}