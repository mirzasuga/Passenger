package com.stickearn.stickpass.view.survey

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.R
import com.stickearn.stickpass.data.survey_repo.SurveyRequest
import com.stickearn.stickpass.model.SurveyMdl
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 2/13/18.
 */
class SurveyPresenterTest {

    @Mock
    lateinit var mSurveyRequest: SurveyRequest
    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: SurveyPresenter

    @Mock
    lateinit var mView: SurveyView


    @Before
    fun setup() {
        mView = Mockito.mock(SurveyView::class.java)
        testScheduler = TestScheduler()
        presenter = SurveyPresenter(mView)
        mSurveyRequest = Mockito.mock(SurveyRequest::class.java)
        CustomSchedulers()
    }

    @Test
    fun loadData() {

        Mockito.`when`(mSurveyRequest.loadData()).thenReturn(Observable.just(getDummyMenu()))
        presenter.loadData()
        testScheduler.triggerActions()
        Mockito.verify(mView).displayData(getDummyMenu())

    }


    private fun getDummyMenu(): List<SurveyMdl> {
        val  mList= mutableListOf<SurveyMdl>()

        mList.add(SurveyMdl(1,"Survey", "you get 100 point from survey HILO ",100,"Feb 1, 2018", 1,R.drawable.survey))
        mList.add(SurveyMdl(2,"StickMart", "you get 100 point from survey HILO",1000,"Feb 1, 2018",1, R.drawable.survey))
        mList.add(SurveyMdl(1,"StickMart", "pembayaran stickmart",100,"Feb 1, 2018",2, R.drawable.survey))
        mList.add(SurveyMdl(2,"Voucher", "Redeem voucher hotel gratis",1000,"Feb 1, 2018",2, R.drawable.survey))

        return mList
    }
}