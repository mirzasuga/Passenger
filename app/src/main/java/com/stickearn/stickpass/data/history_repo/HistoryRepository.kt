package com.stickearn.stickpass.data.history_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.HistoryMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/1/18.
 */
class HistoryRepository() :HistoryRequest,BaseRepository() {
    override fun loadData(): Observable<List<HistoryMdl>> {
      return Observable.just(getDummyMenu())
    }

    /**
     * type 1 = survey
     * type 2 = mart
     * type 3 = voucher
     * status 1= incom
     * status 2 = outcom
     */
    private fun getDummyMenu(): List<HistoryMdl>? {
        val  mList= mutableListOf<HistoryMdl>()

        mList.add(HistoryMdl(1,"Survey", "you get 100 point from survey HILO ",100,"Feb 1, 2018",1,1))
        mList.add(HistoryMdl(2,"StickMart", "you get 100 point from survey HILO",1000,"Feb 1, 2018",2,1))
        mList.add(HistoryMdl(1,"StickMart", "pembayaran stickmart",100,"Feb 1, 2018",2,2))
        mList.add(HistoryMdl(2,"Voucher", "Redeem voucher hotel gratis",1000,"Feb 1, 2018",3,2))

        return mList
    }

    override fun getPointHistory(auth: String, token: String, uuidPoint: String,page:Int): Observable<BaseMdl<List<PointHistoryResponseMdl>>> {

        return mApiClient.getApiServices().getPointHistory(token,auth,uuidPoint,page).flatMap { Observable.just(it) }
    }
}

