package com.stickearn.stickpass.data.mart_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.*
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by macos-vanya on 22/01/18.
 */
class MartRepository : BaseRepository(), MartRequest {




    override fun checkout(auth: String, token: String, payloadRequestData: PayloadRequestBaseMdl<CheckoutRequestMdl>): Observable<BaseMdl<CheckoutResponseMdl>> {
        return mApiClient.getApiServices().postCheckout(token,auth, payloadRequestData).flatMap {   Observable.just(it) }
    }

    override fun loadMart(id:String,auth:String,token:String): Single<List<MartMdl>> {
//        return Observable.just(getDummyMart())

        return mApiClient.mApiServices.getMart(token, auth, id)
                .flatMap { Observable.fromIterable(it.data) }
                .map {
                    MartMdl(it.pricePoint,
                            it.updatedAt,
                            it.productId,
                            it.createdAt,
                            it.martBoxId,
                            it.category,
                            it.productDescription,
                            it.stock,
                            it.productName,
                            it.priceCurrency,
                            it.status,
                            it.mainImage.data.imageUrl)
                }.toList()

    }
    override fun loadMartDetail(boxId: String, productId: String, auth: String, token: String): Observable<MartResponseMdl> {

        return mApiClient.getApiServices().getMartDetail(token,auth,boxId,productId).flatMap { Observable.just(it.data) }
    }
    override fun loadHistoryMart(auth: String, token: String, uuidMart: String): Observable<BaseMdl<List<MartHistoryResponseMdl>>> {
        return mApiClient.getApiServices().getMartHistory(token,auth,uuidMart).flatMap { Observable.just(it) }
    }

}