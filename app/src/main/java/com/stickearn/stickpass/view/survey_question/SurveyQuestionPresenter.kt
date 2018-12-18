package com.stickearn.stickpass.view.survey_question

import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.base.BasePresenter
import com.stickearn.stickpass.data.survey_question_repo.SurveyQuestionRepository
import com.stickearn.stickpass.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by oohyugi on 2/14/18.
 */
class SurveyQuestionPresenter(mView: SurveyQuestionView) : BasePresenter<SurveyQuestionView>(mView) {
     var questionOptionsMdl:MutableList<QuestionOptionsMdl> = mutableListOf()
    var request:SurveyQuestionRepository= SurveyQuestionRepository()

    /*fun getData(id:Int){
        mView.showLoading()
        if (id!=9){
            request.loadData(id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : BaseObserver<BaseMdl<QuestionMdl>>() {
                        override fun onResponseError(s: String) {
                            mView.stopLoading()
                            mView.errorLoading(s)
                        }

                        override fun onResponseSuccess(t: BaseMdl<QuestionMdl>) {
                            mView.stopLoading()

                            if (t.data!=null){
                                mView.displayQuestion(t.data)
                            }else{
                                mView.errorLoading(t.message);
                            }
                        }

                    })

        }else{
            mView.errorLoading("habis")
        }
    }*/

    fun getNextQuestion(auth:String, token:String, survey_uuid:String, question_number:Int){
        mView.showLoading()
        request.getNextQuestion(auth, token, survey_uuid, question_number)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<SurveyQuestionResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<SurveyQuestionResponseMdl>) {
                        mView.stopLoading()
                        mView.onSuccessLoadSurveyQuestion(result)
                    }

                })
    }

    fun submitAnswer(auth:String, token:String, payload: PayloadRequestBaseMdl<SubmitAnswerRequestMdl>){
        mView.showLoading()
        request.submitAnswer(auth, token, payload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<SurveyQuestionResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<SurveyQuestionResponseMdl>) {
                        mView.stopLoading()
                        mView.onSuccessSubmitAnswer(result)
                    }
                })
    }

    fun startSurvey(auth:String, token:String, payload:PayloadRequestBaseMdl<SurveyRequestMdl>){
        mView.showLoading()
        request.startSurvey(auth, token, payload)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : BaseObserver<BaseMdl<SurveyQuestionResponseMdl>>(){
                    override fun onResponseError(errorMessage: String) {
                        mView.stopLoading()
                        mView.errorLoading(errorMessage)
                    }

                    override fun onResponseSuccess(result: BaseMdl<SurveyQuestionResponseMdl>) {
                        mView.stopLoading()
                        mView.onSuccessLoadStartSurvey(result)
                    }

                })
    }
}