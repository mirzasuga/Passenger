package com.stickearn.stickpass.view.notification

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl

/**
 * Created by oohyugi on 2/26/18.
 */
interface NotificationView :BaseView {
    fun displayNotif(t: BaseMdl<List<NotificationResponseMdl>>)
}