package com.stickearn.stickpass.view.scan

import android.util.Log.wtf
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.base.BaseSingleObserver
import com.stickearn.stickpass.data.mart_repo.MartRepository
import com.stickearn.stickpass.model.MartMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/8/18.
 */
class ScanPresenter(mView: ScanView) : BasePresenter<ScanView>(mView) {


    var mMartRequest: MartRepository = MartRepository()

    fun martLoadData(id: String, auth: String, token: String) {
        mView.showLoading()
//        mMartRequest.loadMart(id,auth,token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe ({
//                    result->
//                    mView.stopLoading()
//                    mView.onLoadedMartSuccess(result)
//
//                },{
//                    error->
//                    wtf("error",error.message)
//                    mView.stopLoading()
//                    mView.errorLoading(error.message)
//                })
        mMartRequest.loadMart(id, auth, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseSingleObserver<List<MartMdl>>() {
                    override fun onResponseError(s: String) {
                        wtf("error", s)
                        mView.stopLoading()
                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: List<MartMdl>) {
                        mView.stopLoading()
                        mView.onLoadedMartSuccess(t)
                    }


                })
    }


}