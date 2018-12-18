package com.stickearn.stickpass.view.history

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.history_repo.HistoryRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.HistoryMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/1/18.
 */
class HistoryFragmentPresenter(mView: HistoryView) : BasePresenter<HistoryView>(mView) {



    val mRequest = HistoryRepository()

    fun loadHistory(auth:String,token:String,uuid:String,page:Int){
        mView.showLoading()
        mRequest.getPointHistory(auth,token,uuid,page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object:BaseObserver<BaseMdl<List<PointHistoryResponseMdl>>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<List<PointHistoryResponseMdl>>) {
                        mView.stopLoading()

                            mView.onLoadedDataSuccess(result.data)


                    }

                })

    }

}