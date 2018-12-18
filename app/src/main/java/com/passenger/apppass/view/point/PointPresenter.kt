package com.stickearn.stickpass.view.point


import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.model.PointMdl
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.VoucherMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber



/**
 * Created by macos-vanya on 06/02/18.
 */
class PointPresenter(mView: PointView) : BasePresenter<PointView>(mView) {

     lateinit var mPointRequest: PointRepository

     fun loadData() {

        mPointRequest = PointRepository()

        mPointRequest.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<PointMdl>>(){
                    override fun onResponseError(s: String) {

                    }

                    override fun onResponseSuccess(t: List<PointMdl>) {
                        mView.displayData(t)
                    }
                })
    }

    fun getPoint(auth:String,token:String){
        mPointRequest.getPoint(auth,token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<PointResponseMdl>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: PointResponseMdl) {
                        mView.onGetPointSuccess(result)
                    }

                })
    }

//    fun getVoucher(auth:String,token:String){
//        mView.showLoading()
//        mPointRequest.getVoucher(auth,token)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(object :BaseObserver<List<VoucherMdl>>(){
//                    override fun onResponseError(errorMessage: String) {
//                        mView.stopLoading()
//                        mView.errorLoading(errorMessage)
//                    }
//
//                    override fun onResponseSuccess(result: List<VoucherMdl>) {
//                        mView.stopLoading()
//                    }
//
//                })
//    }

}