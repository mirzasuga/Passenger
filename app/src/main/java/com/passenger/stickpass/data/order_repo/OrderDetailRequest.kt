package com.stickearn.stickpass.data.order_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.OrderDetailMdl
import io.reactivex.Observable

/**
 * Created by macos-vanya on 03/04/18.
 */
interface OrderDetailRequest:BaseRequest {
    fun loadOrderDetail(auth: String, token: String, order_uuid: String): Observable<BaseMdl<OrderDetailMdl>>
}