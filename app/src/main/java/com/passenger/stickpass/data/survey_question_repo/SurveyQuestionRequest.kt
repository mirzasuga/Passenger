package com.stickearn.stickpass.data.survey_question_repo

import com.stickearn.stickpass.model.*
import io.reactivex.Observable

/**
 * Created by oohyugi on 2/15/18.
 */
interface SurveyQuestionRequest {
    fun loadData(id:Int): Observable<BaseMdl<QuestionMdl>>
    fun getNextQuestion(auth:String, token:String, survey_uuid:String, question_number:Int): Observable<BaseMdl<SurveyQuestionResponseMdl>>
    fun startSurvey(auth:String, token:String, payload:PayloadRequestBaseMdl<SurveyRequestMdl>): Observable<BaseMdl<SurveyQuestionResponseMdl>>
    fun submitAnswer(auth:String, token:String, payload:PayloadRequestBaseMdl<SubmitAnswerRequestMdl>): Observable<BaseMdl<SurveyQuestionResponseMdl>>
}