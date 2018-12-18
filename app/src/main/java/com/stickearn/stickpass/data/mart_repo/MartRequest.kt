package com.stickearn.stickpass.data.mart_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.*
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by macos-vanya on 22/01/18.
 */
interface MartRequest : BaseRequest {

    fun loadMart(id:String,auth:String,token:String): Single<List<MartMdl>>

    fun loadMartDetail(boxId:String,productId:String,auth:String,token:String): Observable<MartResponseMdl>

   fun checkout(auth: String, token: String, payloadRequestData: PayloadRequestBaseMdl<CheckoutRequestMdl>):Observable<BaseMdl<CheckoutResponseMdl>>
    fun loadHistoryMart(auth: String,token: String,uuidMart: String):Observable<BaseMdl<List<MartHistoryResponseMdl>>>

}