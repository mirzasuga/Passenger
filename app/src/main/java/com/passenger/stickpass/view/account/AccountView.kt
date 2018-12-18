package com.stickearn.stickpass.view.account

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.ProfileResponseMdl

/**
 * Created by oohyugi on 3/14/18.
 */
interface AccountView:BaseView {
    fun showLoadingPoint()
    fun stopLoadingPoint()
    fun errorLoadingPoint(errorMessage: String)
    fun onSuccessLoadMyPoint(result: PointResponseMdl)
    fun onSuccessLoadProfile(result: ProfileResponseMdl)
}