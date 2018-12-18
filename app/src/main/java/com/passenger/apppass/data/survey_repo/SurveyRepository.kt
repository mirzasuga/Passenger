package com.stickearn.stickpass.data.survey_repo

import com.stickearn.stickpass.R
import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import com.stickearn.stickpass.model.VoucherMdl
import io.reactivex.Observable

/**
 * Created by macos-vanya on 07/02/18.
 */
class SurveyRepository:BaseRepository(), SurveyRequest {

    override fun getSurveyList(auth : String, token : String): Observable<BaseMdl<List<SurveyResponseMdl>>>{
        return mApiClient.getApiServices().getSurveyList(token, auth).flatMap {
            Observable.just(it)
        }
    }

    override fun getSurveyDetail(auth : String, token : String, surveyUuid : String): Observable<SurveyResponseMdl>{
        return mApiClient.getApiServices().getSurveyDetail(token, auth, surveyUuid).flatMap{Observable.just(it.data)}
    }

    override fun loadData(): Observable<List<SurveyMdl>> {
        return Observable.just(getDummyMenu())
    }

    /*val id:Int?=0,
    val name:String?=null,
    val description:String?=null,
    val point:Int?=0,
    val date:String?=null,
    val thumbnail : Int*/

    private fun getDummyMenu(): List<SurveyMdl>? {
        val  mList= mutableListOf<SurveyMdl>()

        mList.add(SurveyMdl(1,"Survey", "you get 100 point from survey HILO ",100,"Feb 1, 2018",1, R.drawable.survey))
        mList.add(SurveyMdl(2,"StickMart", "you get 100 point from survey HILO",1000,"Feb 1, 2018",1,R.drawable.survey))
        mList.add(SurveyMdl(1,"StickMart", "pembayaran stickmart",100,"Feb 1, 2018",2,R.drawable.survey))
        mList.add(SurveyMdl(2,"Voucher", "Redeem voucher hotel gratis",1000,"Feb 1, 2018",2,R.drawable.survey))

        return mList
    }
}