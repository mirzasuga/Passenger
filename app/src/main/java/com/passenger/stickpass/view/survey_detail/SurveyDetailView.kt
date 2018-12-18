package com.stickearn.stickpass.view.survey_detail

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.SurveyResponseMdl

/**
 * Created by macos-fajar on 29/03/18.
 */
interface SurveyDetailView : BaseView {
   fun onSuccessLoadSurveyDetail(result: SurveyResponseMdl)
}