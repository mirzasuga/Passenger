package com.stickearn.stickpass.view.home

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BannerMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.MenuMdl
import com.stickearn.stickpass.model.PointResponseMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface HomeView:BaseView {
    fun displayListBanner(t: List<BannerMdl>)
    fun displayMenuData(t: List<MenuMdl>)
    fun onSuccessLoadMyPoint(result: PointResponseMdl)
    fun showLoadingPoint()
    fun stopLoadingPoint()
    fun errorLoadingPoint(errorMessage: String)
    fun onAuthSuccess(data: LoginResponseMdl)
}