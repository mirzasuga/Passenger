package com.stickearn.stickpass.view.voucher_detail

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.data.voucher.VoucherRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PayloadRequestBaseMdl
import com.stickearn.stickpass.model.PurchasedRequestMdl
import com.stickearn.stickpass.model.PurchasedResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/21/18.
 */
class VoucherDetailPresenter(mView: VoucherDetailView) : BasePresenter<VoucherDetailView>(mView) {

    var mVoucherRequest = VoucherRepository()
    fun purchasedVoucher(auth: String, token: String, data: PayloadRequestBaseMdl<PurchasedRequestMdl>) {
        mView.showLoadingPurchased()
        mVoucherRequest.postPurchasedVoucher(auth, token, data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<PurchasedResponseMdl>>() {
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

    fun redeemVoucher(auth: String, token: String, data: PayloadRequestBaseMdl<PurchasedRequestMdl>) {
        mView.showLoading()
        mVoucherRequest.postRedeemVoucher(auth, token, data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<PurchasedResponseMdl>>() {
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<PurchasedResponseMdl>) {
                        mView.stopLoading()
                        mView.onRedeemSuccess(result)
                    }

                })
    }
}