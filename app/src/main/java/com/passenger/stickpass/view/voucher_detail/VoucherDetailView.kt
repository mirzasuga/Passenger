package com.stickearn.stickpass.view.voucher_detail

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PurchasedResponseMdl

/**
 * Created by oohyugi on 3/21/18.
 */
interface VoucherDetailView :BaseView {
    fun showLoadingPurchased()
    fun stopLoadingPurchased()
    fun onPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>)
    fun onRedeemSuccess(result: BaseMdl<PurchasedResponseMdl>)
}