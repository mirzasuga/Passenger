package com.stickearn.stickpass.view.order_complete

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.OrderDetailMdl

/**
 * Created by macos-vanya on 03/04/18.
 */
interface OrderCompleteView:BaseView {
    fun onSuccessLoadDetailMart(result: BaseMdl<OrderDetailMdl>)
}