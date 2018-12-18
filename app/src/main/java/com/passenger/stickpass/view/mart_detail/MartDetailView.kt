package com.stickearn.stickpass.view.mart_detail

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.MartResponseMdl

/**
 * Created by oohyugi on 3/12/18.
 */
interface MartDetailView:BaseView {
    fun onSuccessLoadDetailMart(result: MartResponseMdl)
}