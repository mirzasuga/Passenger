package com.stickearn.stickpass.data.notification_repo

import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.model.NotificationMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/26/18.
 */
interface NotificationRequest {

    fun loadNotification(): Observable<List<NotificationMdl>>
    fun loadNotificationCount(auth:String,token:String,page:Int,room:Int): Observable<BaseMdl<List<NotificationResponseMdl>>>
    fun loadNotificationDetail(auth:String,token:String,id:Int): Observable<BaseMdl<NotificationResponseMdl>>
}