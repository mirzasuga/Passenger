package com.stickearn.stickpass.view.scan

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.MartMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface ScanView :BaseView{
    fun onLoadedMartSuccess(data: List<MartMdl>)

}