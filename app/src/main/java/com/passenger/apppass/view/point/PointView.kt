package com.stickearn.stickpass.view.point

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.PointMdl
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.VoucherMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface PointView:BaseView {
    fun displayData(t: List<PointMdl>)
    fun onGetPointSuccess(result: PointResponseMdl)

}