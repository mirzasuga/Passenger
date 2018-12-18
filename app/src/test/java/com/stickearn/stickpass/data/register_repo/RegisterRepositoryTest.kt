package com.stickearn.stickpass.data.register_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.model.*
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/8/18.
 */
class RegisterRepositoryTest{
    @Mock
    lateinit var mRequest: RegisterRequest

    @Before
    fun setUp() {
        mRequest = Mockito.mock(RegisterRequest::class.java)
        CustomSchedulers()

    }

    @Test
    fun testPostRegister() {
//        val registerRequestMdl = RegisterRequestMdl(name = "testing  from android", email = "testingandroid@gmail.com",phone_number = "08455q53543",password = "123456",passwordConfirmation = "123456" )
//        mRequest = RegisterRepository()
//        val testSubscriber = TestObserver<BaseMdl<RegisterResponseMdl>>()
//        mRequest.postRegister(registerRequestMdl)?.subscribe(testSubscriber)
//
//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
//        testSubscriber.assertNoErrors()
//        testSubscriber.assertValueCount(1)
    }
}