package com.stickearn.stickpass.data.point_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by macos-vanya on 06/02/18.
 */

interface PointRequest : BaseRequest{

    fun loadData(): Observable<List<PointMdl>>
    fun getPoint(auth:String,token:String):Observable<PointResponseMdl>
    fun getPointHistory(auth:String,token:String,uuidPoint:String):Observable<BaseMdl<List<PointHistoryResponseMdl>>>
    fun getVoucherHistory(auth:String,token:String):Observable<List<VoucherMdl>>
    fun postPurchasedVoucher(auth: String,token: String,data:PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>
    fun postRedeemVoucher(auth: String,token: String,data:PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>
}