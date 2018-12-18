package com.stickearn.stickpass.view.mart

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.mart_repo.MartRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.CheckoutRequestMdl
import com.stickearn.stickpass.model.PayloadRequestBaseMdl
import com.stickearn.stickpass.model.CheckoutResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/8/18.
 */
class MartPresenter(mView: MartView) : BasePresenter<MartView>(mView) {

    var mRequest = MartRepository()
    fun postCheckout(auth:String, token:String, basePayloadRequestBaseMdl: PayloadRequestBaseMdl<CheckoutRequestMdl>){
        mView.showLoading()
        mRequest.checkout(auth,token, basePayloadRequestBaseMdl)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<CheckoutResponseMdl>>(){
                    override fun onResponseError(s: String) {
                        mView.stopLoading()
                        mView.errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<CheckoutResponseMdl>) {
                        mView.stopLoading()
                        if (t.status){
                            mView.onCheckoutSuccess(t.data)
                        }else{
                            mView.errorLoading(t.message)
                        }

                    }

                })
    }
}