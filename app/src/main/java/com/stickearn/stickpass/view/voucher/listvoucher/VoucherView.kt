package com.stickearn.stickpass.view.voucher.listvoucher

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PurchasedResponseMdl
import com.stickearn.stickpass.model.VoucherMdl

/**
 * Created by oohyugi on 3/19/18.
 */
interface VoucherView :BaseView {
    fun onGetVoucherSuccess(result: BaseMdl<List<VoucherMdl>>)
    fun onPurchasedSuccess(result: BaseMdl<PurchasedResponseMdl>)
    fun showLoadingPurchased()
    fun stopLoadingPurchased()
}