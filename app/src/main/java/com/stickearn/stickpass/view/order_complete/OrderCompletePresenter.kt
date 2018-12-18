package com.stickearn.stickpass.view.order_complete

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.order_repo.OrderDetailRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.OrderDetailMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by macos-vanya on 03/04/18.
 */
class OrderCompletePresenter(mView: OrderCompleteView) : BasePresenter<OrderCompleteView>(mView) {

    var mRequest = OrderDetailRepository()
    fun getOrderDetail(order_uuid:String,auth:String,token:String){
        mView.showLoading()
        mRequest.loadOrderDetail(auth,token, order_uuid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<OrderDetailMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<OrderDetailMdl>) {
                        mView.stopLoading()
                        mView.onSuccessLoadDetailMart(result)
                    }

                })
    }
}