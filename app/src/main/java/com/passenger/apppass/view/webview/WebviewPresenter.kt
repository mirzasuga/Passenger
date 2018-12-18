package com.stickearn.stickpass.view.webview

import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.policy.PolicyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/19/18.
 */
class WebviewPresenter(mView: WebviewView) : BasePresenter<WebviewView>(mView) {

    val mRequest = PolicyRepository()
    fun loadPolicy(auth:String,token:String,type:Int){
        mView.showLoading()
        mRequest.getPolicy(auth,token,type).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result->
                    mView.stopLoading()
                    mView.displayPolicy(result)
                },{
                    error->
                    mView.stopLoading()
                    mView.errorLoading(error.message.toString())

                })

    }
}