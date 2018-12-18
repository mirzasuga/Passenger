package com.stickearn.stickpass.view.voucher.myvoucher

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.data.voucher.VoucherRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.VoucherMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/19/18.
 */
class MyVoucherPresenter(mView: MyVoucherView) : BasePresenter<MyVoucherView>(mView) {

    var mVoucherRequest = VoucherRepository()
    fun getVoucher(auth:String,token:String,page:Int){
        mView.showLoading()
        mVoucherRequest.getMyVoucher(auth,token,page)
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
}