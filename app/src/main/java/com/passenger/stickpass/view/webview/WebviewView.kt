package com.stickearn.stickpass.view.webview

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.PolicyMdl

/**
 * Created by oohyugi on 3/19/18.
 */
interface WebviewView:BaseView {
    fun displayPolicy(result: PolicyMdl)
}