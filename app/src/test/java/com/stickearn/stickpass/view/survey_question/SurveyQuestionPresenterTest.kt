package com.stickearn.stickpass.view.survey_question

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.data.survey_question_repo.SurveyQuestionRepository
import com.stickearn.stickpass.data.survey_question_repo.SurveyQuestionRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.QuestionMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch

/**
 * Created by oohyugi on 2/20/18.
 */
class SurveyQuestionPresenterTest{
    @Mock
    lateinit var mSurveyRequest: SurveyQuestionRequest
    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: SurveyQuestionPresenter
    var data:QuestionMdl = QuestionMdl()
    lateinit var baseMdl:BaseMdl<QuestionMdl>

    @Mock
    lateinit var mView: SurveyQuestionView
val coutdDownLatch:CountDownLatch = CountDownLatch(1)

    @Before
    fun setup() {
        mView = Mockito.mock(SurveyQuestionView::class.java)
        testScheduler = TestScheduler()
        presenter = SurveyQuestionPresenter(mView)
        mSurveyRequest = Mockito.mock(SurveyQuestionRequest::class.java)
        CustomSchedulers()
    }

    @Test
    fun loadData() {
        mSurveyRequest = SurveyQuestionRepository()
        mSurveyRequest.loadData(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<BaseMdl<QuestionMdl>>() {
                    override fun onResponseError(s: String) {
//                        Mockito.verify(mView).errorLoading(s)
                    }

                    override fun onResponseSuccess(t: BaseMdl<QuestionMdl>) {
//                        Mockito.verify(mView).displayQuestion(t.data)
                        data = t.data
                        coutdDownLatch.countDown()

                        Mockito.verify(mView).displayQuestion(data)
                    }

                })
        coutdDownLatch.await()
    }


}