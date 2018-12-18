package com.stickearn.stickpass.data.voucher

import com.stickearn.stickpass.R
import com.stickearn.stickpass.api.ApiClient
import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.helper.Constant
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by macos-vanya on 06/02/18.
 */
class VoucherRepository :BaseRepository(),VoucherRequest{
    override fun getMyVoucher(auth: String, token: String, page: Int): Observable<BaseMdl<List<VoucherMdl>>> {
        return mApiClient.getApiServices().getMyVoucher(token,auth,page).flatMap {
            Observable.just(it)

        }
    }



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



//    override fun getVoucherHistory(auth: String, token: String): Observable<List<VoucherMdl>> {
//        return mApiClient.getApiServices().getVoucherHistory(token,auth).flatMap {
//            Observable.just(it.data)
//        }
//    }

    override fun getVoucher(auth: String, token: String,page:Int): Observable<BaseMdl<List<VoucherMdl>>> {
        return mApiClient.getApiServices().getVoucher(token,auth,page).flatMap {
            Observable.just(it)
        }
    }


}