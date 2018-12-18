package com.stickearn.stickpass.view.mart

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.CheckoutResponseMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface MartView:BaseView {
    fun onCheckoutSuccess(data: CheckoutResponseMdl)
}