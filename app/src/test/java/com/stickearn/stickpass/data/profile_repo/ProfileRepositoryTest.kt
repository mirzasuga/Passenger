package com.stickearn.stickpass.data.profile_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.data.notification_repo.NotificationRepository
import com.stickearn.stickpass.data.notification_repo.NotificationRequest
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import com.stickearn.stickpass.model.ProfileResponseMdl
import com.stickearn.stickpass.model.UserMdl
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/14/18.
 */
class ProfileRepositoryTest{
    @Mock
    lateinit var mRequest: ProfileRequest

    @Before
    fun setUp() {
        mRequest = Mockito.mock(ProfileRequest::class.java)
        CustomSchedulers()

    }
    @Test
    fun loadProfile() {

        mRequest = ProfileRepository()
        val testSubscriber = TestObserver<UserMdl>()
        mRequest.loadProfile("eyJhY2NvdW50Ijp7ImlkIjo4OSwibmFtZSI6IlRlc3QgMTQiLCJlbWFpbCI6InRlc3QxMTExMTRAdGVzdC5jb20iLCJhY2Nlc3MiOlt7Imdyb3VwIjoicGFzc2VuZ2VyIiwidXVpZF9wYXNzZW5nZXIiOiIzYWI2MzgxOS00NjhkLTQ3NTgtYTYxOC05NDE1NTA0NjAxMWMiLCJtZW51X3Bhc3NlbmdlciI6W119XSwicGhvbmUiOm51bGwsInByb3ZpZGVyIjpudWxsLCJwcm92aWRlcl9pZCI6bnVsbCwiYWN0aXZlIjoxLCJjcmVhdGVkX2F0Ijp7ImRhdGUiOiIyMDE4LTAzLTEyIDE1OjQ4OjM0LjAwMDAwMCIsInRpbWV6b25lX3R5cGUiOjMsInRpbWV6b25lIjoiQXNpYVwvSmFrYXJ0YSJ9LCJ1cGRhdGVkX2F0Ijp7ImRhdGUiOiIyMDE4LTAzLTEyIDE1OjQ4OjM0LjAwMDAwMCIsInRpbWV6b25lX3R5cGUiOjMsInRpbWV6b25lIjoiQXNpYVwvSmFrYXJ0YSJ9fX0=","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMzUuMTg2LjE0Ni4xNC9hcGkvdjEvYXV0aCIsImlhdCI6MTUyMTAyMDUyNCwiZXhwIjoxNTIxMDI0MTI0LCJuYmYiOjE1MjEwMjA1MjQsImp0aSI6ImJleTZQaWJTemdJaHBrdUkiLCJzdWIiOjg5LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.w9m0FfYU4C2mOMXHsj6pJOfoV9qlmiqECkYYQQBnfpg").subscribe(testSubscriber)

//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }

}