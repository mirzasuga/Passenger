package com.stickearn.stickpass.data.survey_question_repo

import com.stickearn.stickpass.data.BaseRepository
import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/15/18.
 */
class SurveyQuestionRepository:BaseRepository(),SurveyQuestionRequest {
    override fun getNextQuestion(auth: String, token: String, survey_uuid:String, question_number:Int): Observable<BaseMdl<SurveyQuestionResponseMdl>> {
        return mApiClient.mApiServices.getNextQuestion(auth, token, survey_uuid, question_number).flatMap{Observable.just(it)}
    }

    override fun startSurvey(auth: String, token: String, payload:PayloadRequestBaseMdl<SurveyRequestMdl>): Observable<BaseMdl<SurveyQuestionResponseMdl>> {
        return mApiClient.mApiServices.startSurvey(auth, token, payload).flatMap{Observable.just(it)}
    }

    override fun submitAnswer(auth: String, token: String, payload:PayloadRequestBaseMdl<SubmitAnswerRequestMdl>): Observable<BaseMdl<SurveyQuestionResponseMdl>> {
        return mApiClient.mApiServices.submitAnswer(auth, token, payload).flatMap{Observable.just(it)}
    }

    override fun loadData(id:Int): Observable<BaseMdl<QuestionMdl>> {
//        mApiClient = ApiClient("https://private-e18b20-stickpass.apiary-mock.com/")
        return mApiClient.mApiServices.getQuestion(id).flatMap {
            Observable.just(it)
        }


    }
}