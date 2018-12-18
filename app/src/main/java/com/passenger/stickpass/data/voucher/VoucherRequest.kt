package com.stickearn.stickpass.data.voucher

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by macos-vanya on 06/02/18.
 */

interface VoucherRequest : BaseRequest{

    fun getVoucher(auth:String,token:String,page:Int):Observable<BaseMdl<List<VoucherMdl>>>
    fun getMyVoucher(auth:String,token:String,page: Int):Observable<BaseMdl<List<VoucherMdl>>>
    fun postPurchasedVoucher(auth: String,token: String,data:PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>
    fun postRedeemVoucher(auth: String,token: String,data:PayloadRequestBaseMdl<PurchasedRequestMdl>):Observable<BaseMdl<PurchasedResponseMdl>>
}