package com.stickearn.stickpass.data.notification_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.model.NotificationMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/26/18.
 */
class NotificationRepository:NotificationRequest, BaseRepository() {
    override fun loadNotificationDetail(auth: String, token: String, id:Int): Observable<BaseMdl<NotificationResponseMdl>> {
        return mApiClient.getApiServices().getNotifDetail(token,auth,id).flatMap { Observable.just(it) }
    }

    override fun loadNotificationCount(auth: String, token: String,page:Int,room:Int): Observable<BaseMdl<List<NotificationResponseMdl>>> {
        return mApiClient.getApiServices().getNotificationCount(token,auth,page,room).flatMap { Observable.just(it) }
    }


    override fun loadNotification(): Observable<List<NotificationMdl>> {

        return Observable.just(getDummy())
    }
    private fun getDummy(): MutableList<NotificationMdl> {
        val  mList= mutableListOf<NotificationMdl>()

        mList.add(NotificationMdl(1, "Congratulations you get 100 point", "03 Mar 2018"))
        mList.add(NotificationMdl(2, "Congratulations you get 100 point", "03 Mar 2018"))
        mList.add(NotificationMdl(3, "Congratulations you get 100 point", "03 Mar 2018"))
        return mList
    }

}