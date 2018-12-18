package com.stickearn.stickpass.data.order_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.OrderDetailMdl
import io.reactivex.Observable

/**
 * Created by macos-vanya on 03/04/18.
 */
class OrderDetailRepository:BaseRepository(),OrderDetailRequest {

    override fun loadOrderDetail(auth: String, token: String, order_uuid: String): Observable<BaseMdl<OrderDetailMdl>> {
        return mApiClient.getApiServices().getOrderDetail(token,auth, order_uuid).flatMap {   Observable.just(it) }
    }
}