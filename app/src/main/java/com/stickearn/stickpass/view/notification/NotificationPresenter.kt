package com.stickearn.stickpass.view.notification

import android.util.Log.wtf
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.notification_repo.NotificationRepository
import com.stickearn.stickpass.data.notification_repo.NotificationRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/26/18.
 */
class NotificationPresenter(mView: NotificationView) : BasePresenter<NotificationView>(mView) {

    lateinit var mRequest:NotificationRequest
    fun loadNotif(auth: String, token: String, page: Int,room:Int){
        mRequest = NotificationRepository()
        mView.showLoading()
        mRequest.loadNotificationCount(auth,token,page,room).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<List<NotificationResponseMdl>>>(){
                    override fun onResponseError(errorMessage: String) {
                        wtf("onErrorNotif",errorMessage)
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(t: BaseMdl<List<NotificationResponseMdl>>) {
                        mView.stopLoading()
                        mView.displayNotif(t)
                    }

                })
//
//        getDummyData()
//        mView.displayNotif(getDummyData())

    }

    var mutableList:MutableList<NotificationResponseMdl>  = mutableListOf()
    lateinit var data : NotificationResponseMdl
    private fun getDummyData():MutableList<NotificationResponseMdl> {
        for (i in 1..10) {
            mutableList.add(NotificationResponseMdl(1,"Test $i",i,"","test $i","","2018-03-16 14:43:02"))
        }

        return mutableList
    }
}