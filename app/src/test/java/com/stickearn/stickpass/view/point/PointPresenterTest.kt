package com.stickearn.stickpass.view.point
import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.R
import com.stickearn.stickpass.data.point_repo.PointRequest
import com.stickearn.stickpass.model.PointMdl
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import io.reactivex.schedulers.TestScheduler
import org.junit.Before



/**
 * Created by oohyugi on 2/12/18.
 */
class PointPresenterTest {

    @Mock
    lateinit var mPointRequest: PointRequest
    private lateinit var testScheduler: TestScheduler
    private lateinit var presenter: PointPresenter

    @Mock
    lateinit var mView: PointView


    @Before
    fun setup() {
        mView = Mockito.mock(PointView::class.java)
        testScheduler = TestScheduler()
        presenter = PointPresenter(mView)
        mPointRequest = Mockito.mock(PointRequest::class.java)
        CustomSchedulers()
    }

    @Test
    fun loadData() {

        `when`(mPointRequest.loadData()).thenReturn(Observable.just(getDummyMenu()))
        presenter.loadData()
        testScheduler.triggerActions()
        verify(mView).displayData(getDummyMenu())

    }

    private fun getDummyMenu(): List<PointMdl> {
        val mList = mutableListOf<PointMdl>()

        mList.add(PointMdl(1, "Survey", "you get 100 point from survey HILO ", 100, "Feb 1, 2018", 1, R.drawable.survey))
        mList.add(PointMdl(2, "StickMart", "you get 100 point from survey HILO", 1000, "Feb 1, 2018", 2, R.drawable.survey))
        mList.add(PointMdl(1, "StickMart", "pembayaran stickmart", 100, "Feb 1, 2018", 2, R.drawable.survey))
        mList.add(PointMdl(2, "Voucher", "Redeem voucher hotel gratis", 1000, "Feb 1, 2018", 1, R.drawable.survey))

        return mList
    }

}