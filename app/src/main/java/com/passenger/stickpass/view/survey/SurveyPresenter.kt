package com.stickearn.stickpass.view.survey

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.survey_repo.SurveyRepository
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.SurveyMdl
import com.stickearn.stickpass.model.SurveyResponseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by macos-vanya on 07/02/18.
 */
class SurveyPresenter(mView: SurveyView) : BasePresenter<SurveyView>(mView) {

   var mSurveyListRequest = SurveyRepository()
    fun getSurveyList(auth:String, token:String){
        mView.showLoading()
        mSurveyListRequest.getSurveyList(auth, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<List<SurveyResponseMdl>>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<List<SurveyResponseMdl>>) {
                        mView.stopLoading()
                        mView.onGetSurveyList(result)
                    }

                })


    }

    fun loadData() {
        mSurveyListRequest.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<List<SurveyMdl>>() {
                    override fun onResponseError(s: String) {

                    }

                    override fun onResponseSuccess(t: List<SurveyMdl>) {

                        mView.displayData(t)
                    }
                })
    }

}