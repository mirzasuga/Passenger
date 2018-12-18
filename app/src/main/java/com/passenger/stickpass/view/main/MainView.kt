package com.stickearn.stickpass.view.main

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.LoginResponseMdl

/**
 * Created by oohyugi on 1/15/18.
 */
interface MainView: BaseView {
    fun onSuccessLoadUnReadNotif(mListUnReadNotif: Int)


}