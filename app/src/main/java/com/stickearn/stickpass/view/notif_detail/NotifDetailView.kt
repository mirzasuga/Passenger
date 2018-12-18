package com.stickearn.stickpass.view.notif_detail

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.NotificationResponseMdl

/**
 * Created by oohyugi on 3/22/18.
 */
interface NotifDetailView:BaseView {
    fun displayDetail(data: NotificationResponseMdl)
}