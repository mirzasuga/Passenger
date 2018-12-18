package com.stickearn.stickpass.view.survey

import com.stickearn.stickpass.base.BaseView
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl

/**
 * Created by oohyugi on 2/8/18.
 */
interface SurveyView:BaseView {
    fun displayData(t: List<SurveyMdl>)
    fun onGetSurveyList(result: BaseMdl<List<SurveyResponseMdl>>)
}