package com.stickearn.stickpass.data.survey_question_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.QuestionMdl
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.mockito.Mock
import org.junit.Test
import org.mockito.Mockito


/**
 * Created by oohyugi on 2/21/18.
 */
class SurveyQuestionRepositoryTest{
    @Mock
    lateinit var mSurveyRequest: SurveyQuestionRequest

    @Before
    fun setUp() {
        mSurveyRequest = Mockito.mock(SurveyQuestionRequest::class.java)
        CustomSchedulers()

    }

    @Test
    fun testgetQuestion() {
        mSurveyRequest = SurveyQuestionRepository()
        val testSubscriber = TestObserver<BaseMdl<QuestionMdl>>()
        mSurveyRequest.loadData(1)
                .subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }

}