package com.stickearn.stickpass.data.point_repo

import com.stickearn.stickpass.R
import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.helper.Constant
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by macos-vanya on 06/02/18.
 */
class PointRepository:BaseRepository(),PointRequest{
    override fun postRedeemVoucher(auth: String, token: String, data: PayloadRequestBaseMdl<PurchasedRequestMdl>): Observable<BaseMdl<PurchasedResponseMdl>> {
        return mApiClient.getApiServices().postRedeemVoucher(token,auth,data).flatMap {
            Observable.just(it)
        }
    }

    override fun postPurchasedVoucher(auth: String, token: String, data: PayloadRequestBaseMdl<PurchasedRequestMdl>): Observable<BaseMdl<PurchasedResponseMdl>> {
        return mApiClient.getApiServices().postPurchasedVoucher(token,auth,data).flatMap {
            Observable.just(it)
        }
    }


    override fun getVoucherHistory(auth: String, token: String): Observable<List<VoucherMdl>> {
        return mApiClient.getApiServices().getVoucherHistory(token,auth).flatMap {
            Observable.just(it.data)
        }
    }

    override fun  getPoint(auth: String, token: String): Observable<PointResponseMdl> {

        return mApiClient.getApiServices().getPoint(token,auth).flatMap { Observable.just(it.data) }
    }

    override fun getPointHistory(auth: String, token: String, uuidPoint: String): Observable<BaseMdl<List<PointHistoryResponseMdl>>> {

        return mApiClient.getApiServices().getPointHistory(token,auth,uuidPoint,1).flatMap { Observable.just(it) }
    }

    override fun loadData(): Observable<List<PointMdl>> {
        return Observable.just(getDummyMenu())
    }

    /*val id:Int?=0,
    val name:String?=null,
    val description:String?=null,
    val point:Int?=0,
    val date:String?=null,
    val type:Int?=0*/

    private fun getDummyMenu(): List<PointMdl>? {
        val  mList= mutableListOf<PointMdl>()

        mList.add(PointMdl(1,"Survey", "you get 100 point from survey HILO ",100,"Feb 1, 2018",1, R.drawable.survey))
        mList.add(PointMdl(2,"StickMart", "you get 100 point from survey HILO",1000,"Feb 1, 2018",2, R.drawable.survey))
        mList.add(PointMdl(1,"StickMart", "pembayaran stickmart",100,"Feb 1, 2018",2, R.drawable.survey))
        mList.add(PointMdl(2,"Voucher", "Redeem voucher hotel gratis",1000,"Feb 1, 2018",1, R.drawable.survey))

        return mList
    }


}