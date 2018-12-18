package com.stickearn.stickpass.view.voucher.listvoucher

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.data.voucher.VoucherRepository
import com.stickearn.stickpass.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/19/18.
 */
class VoucherPresenter(mView: VoucherView) : BasePresenter<VoucherView>(mView) {


   var mVoucherRequest =VoucherRepository()
    fun getVoucher(auth: String, token: String, currentPage: Int){
        mView.showLoading()
        mVoucherRequest.getVoucher(auth,token,currentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<List<VoucherMdl>>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<List<VoucherMdl>>) {
                        mView.stopLoading()
                        mView.onGetVoucherSuccess(result)
                    }
                })
    }

    fun purchasedVoucher(auth: String, token: String, data:PayloadRequestBaseMdl<PurchasedRequestMdl>){
        mView.showLoadingPurchased()
        mVoucherRequest.postPurchasedVoucher(auth,token,data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<PurchasedResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoadingPurchased()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<PurchasedResponseMdl>) {
                        mView.stopLoadingPurchased()
                        mView.onPurchasedSuccess(result)
                    }

                })
    }
}