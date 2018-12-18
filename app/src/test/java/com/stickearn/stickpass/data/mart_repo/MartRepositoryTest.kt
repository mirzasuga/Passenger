package com.stickearn.stickpass.data.mart_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.model.*
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/9/18.
 */
class MartRepositoryTest{
    @Mock
    lateinit var mRequest: MartRequest

    @Before
    fun setUp() {
        mRequest = Mockito.mock(MartRequest::class.java)
        CustomSchedulers()

    }

    @Test
    fun testPostCheckout() {
        val list :MutableList<MartItemMdl> = mutableListOf()
        list.add(MartItemMdl(10000,2,1,10))
        list.add(MartItemMdl(20000,3,4,20))
        val checkoutData = CheckoutRequestMdl("d9cf8b91-264c-41e1-9011-eaca3886967a",2000,list,20000,"1233","cash")
        val checkoutBase = PayloadRequestBaseMdl(checkoutData)
        mRequest = MartRepository()
        val testSubscriber = TestObserver<BaseMdl<CheckoutResponseMdl>>()
        mRequest.checkout("eyJhY2NvdW50Ijp7Im9hdXRoIjogIjEzMyIsIm1hcnQiOiAiMjA5YzNhZWQtNTJhNi00NGIxLWJlMzItMzQ1OWUwODNhYjU2IiwicG9pbnRzIjogImIwZjE5OTU3LTRkMDctNDM2Ny1iYTBlLWVkNzllZGFmMmQwZCIsInN1cnZleSI6ICJhODVjOGM2OS0yNjBhLTRhODQtOGM5Zi0yZGE5ZjJkOTdiNTIiLCJlYXJuIjogIjU5ZGU5ZTYyLTM0ODEtNDUxZC04OWNhLTNkNzMxYWVjZDM1NyIsIm5hbWUiOiJEZWR5IFNldGlhd2FuIiwiZW1haWwiOiJkZWR5c0BzdGlja2Vhcm4uY29tIiwicm9sZSI6ImFkbWluIn19","1233",checkoutBase).subscribe(testSubscriber)

//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }

    @Test
    fun testLoadHistoryMart() {

        mRequest = MartRepository()
        val testSubscriber = TestObserver<BaseMdl<List<MartHistoryResponseMdl>>>()
        mRequest.loadHistoryMart("eyJhY2NvdW50Ijp7Im9hdXRoIjogIjEzMyIsIm1hcnQiOiAiMjA5YzNhZWQtNTJhNi00NGIxLWJlMzItMzQ1OWUwODNhYjU2IiwicG9pbnRzIjogImIwZjE5OTU3LTRkMDctNDM2Ny1iYTBlLWVkNzllZGFmMmQwZCIsInN1cnZleSI6ICJhODVjOGM2OS0yNjBhLTRhODQtOGM5Zi0yZGE5ZjJkOTdiNTIiLCJlYXJuIjogIjU5ZGU5ZTYyLTM0ODEtNDUxZC04OWNhLTNkNzMxYWVjZDM1NyIsIm5hbWUiOiJEZWR5IFNldGlhd2FuIiwiZW1haWwiOiJkZWR5c0BzdGlja2Vhcm4uY29tIiwicm9sZSI6ImFkbWluIn19","1233","209c3aed-52a6-44b1-be32-3459e083ab56")
                .subscribe(testSubscriber)

//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }
}