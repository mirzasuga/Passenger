package com.stickearn.stickpass.view.notif_detail

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.notification_repo.NotificationRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 3/22/18.
 */
class NotifDetailPresenter(mView: NotifDetailView) : BasePresenter<NotifDetailView>(mView) {
    val mRequest = NotificationRepository()
    fun getDetail(auth: String, token: String, mId: Int){
        mView.showLoading()
        mRequest.loadNotificationDetail(auth,token,mId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<NotificationResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<NotificationResponseMdl>) {
                        mView.stopLoading()
                        mView.displayDetail(result.data)
                    }

                })
    }
}