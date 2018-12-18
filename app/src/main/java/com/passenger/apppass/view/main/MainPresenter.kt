package com.stickearn.stickpass.view.main

import android.util.Log.wtf
import com.google.gson.Gson
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.login_repo.LoginRepository
import com.stickearn.stickpass.data.main_repo.MainRepository
import com.stickearn.stickpass.data.notification_repo.NotificationRepository
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.model.PointResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by oohyugi on 1/15/18.
 */
class MainPresenter(mView: MainView) : BasePresenter<MainView>(mView) {

    lateinit var mMainRequest: MainRepository
    val mRequestNotif = NotificationRepository()

    var mListUnReadNotif:MutableList<Int> = mutableListOf()
    fun getNotificationCount(auth:String,token:String){
        mRequestNotif.loadNotificationCount(auth,token,1,1).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :BaseObserver<BaseMdl<List<NotificationResponseMdl>>>(){
                    override fun onResponseError(errorMessage: String) {
                        wtf("onError",errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<List<NotificationResponseMdl>>) {

                        if (result.status){
                            mView.onSuccessLoadUnReadNotif(result.meta.totalUnread)


                        }else{
                            mView.errorLoading(result.message)
                        }

                    }

                })
    }




}