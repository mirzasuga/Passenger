package com.stickearn.stickpass.data.survey_repo

import com.stickearn.stickpass.data.BaseRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import io.reactivex.Observable

/**
 * Created by macos-vanya on 07/02/18.
 */
interface SurveyRequest : BaseRequest {

    fun loadData(): Observable<List<SurveyMdl>>
    fun getSurveyList(auth: String, token: String): Observable<BaseMdl<List<SurveyResponseMdl>>>
    fun getSurveyDetail(auth: String, token: String, surveyUuid:String): Observable<SurveyResponseMdl>
}