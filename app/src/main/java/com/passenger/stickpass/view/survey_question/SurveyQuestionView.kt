package com.stickearn.stickpass.view.survey_question

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.QuestionMdl
import com.stickearn.stickpass.model.SurveyQuestionResponseMdl

/**
 * Created by oohyugi on 2/14/18.
 */
interface SurveyQuestionView : BaseView{
//    fun displayQuestion(dummyData: QuestionMdl)
    fun onSuccessLoadSurveyQuestion(result:BaseMdl<SurveyQuestionResponseMdl>)
    fun onSuccessLoadStartSurvey(result:BaseMdl<SurveyQuestionResponseMdl>)
    fun onSuccessSubmitAnswer(result:BaseMdl<SurveyQuestionResponseMdl>)

}