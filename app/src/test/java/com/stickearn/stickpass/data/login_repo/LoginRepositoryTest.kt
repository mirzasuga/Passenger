package com.stickearn.stickpass.data.login_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.data.register_repo.RegisterRepository
import com.stickearn.stickpass.data.register_repo.RegisterRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.LoginResponseMdl
import com.stickearn.stickpass.model.RegisterRequestMdl
import com.stickearn.stickpass.model.RegisterResponseMdl
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/9/18.
 */
class LoginRepositoryTest{
    @Mock
    lateinit var mRequest: LoginRequest

    @Before
    fun setUp() {
        mRequest = Mockito.mock(LoginRequest::class.java)
        CustomSchedulers()

    }

    @Test
    fun testPostRegister() {
        mRequest = LoginRepository()
        val testSubscriber = TestObserver<BaseMdl<LoginResponseMdl>>()
        mRequest.postAuth("hudson.amie@kihn.com","a").subscribe(testSubscriber)

//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }
}