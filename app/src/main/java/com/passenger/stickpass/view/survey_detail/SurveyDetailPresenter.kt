package com.stickearn.stickpass.view.survey_detail

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.survey_repo.SurveyRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by macos-fajar on 29/03/18.
 */
class SurveyDetailPresenter(mView:SurveyDetailView) : BasePresenter<SurveyDetailView>(mView) {

    var mSurveyDetailRequest = SurveyRepository()
    fun getSurveyDetail(auth:String, token:String, surveyUuid:String){
        mView.showLoading()
        mSurveyDetailRequest.getSurveyDetail(auth, token, surveyUuid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<SurveyResponseMdl>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: SurveyResponseMdl) {
                        mView.stopLoading()
                        mView.onSuccessLoadSurveyDetail(result)
                    }

                })


    }
}