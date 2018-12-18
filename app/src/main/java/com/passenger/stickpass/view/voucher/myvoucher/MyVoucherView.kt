package com.stickearn.stickpass.view.voucher.myvoucher

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.VoucherMdl

/**
 * Created by oohyugi on 3/19/18.
 */
interface MyVoucherView :BaseView {
    fun onGetVoucherSuccess(result: BaseMdl<List<VoucherMdl>>)
}