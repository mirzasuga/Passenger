package com.stickearn.stickpass.view.history

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.PointHistoryResponseMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface HistoryView :BaseView {
    fun onLoadedDataSuccess(t: List<PointHistoryResponseMdl>)
}