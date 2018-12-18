package com.stickearn.stickpass.data.notification_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.NotificationResponseMdl
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/13/18.
 */
class NotificationRepositoryTest {

    @Mock
    lateinit var mRequest: NotificationRequest

    @Before
    fun setUp() {
        mRequest = Mockito.mock(NotificationRequest::class.java)
        CustomSchedulers()

    }
    @Test
    fun loadNotificationCount() {

        mRequest = NotificationRepository()
        val testSubscriber = TestObserver<BaseMdl<List<NotificationResponseMdl>>>()
        mRequest.loadNotificationCount("eyJhY2NvdW50Ijp7ImlkIjoxLCJuYW1lIjoiSm9keSBDYXJ0ZXIiLCJlbWFpbCI6Imh1ZHNvbi5hbWllQGtpaG4uY29tIiwiYWNjZXNzIjpbeyJncm91cCI6ImFkbWluIiwidXVpZF9hZG1pbiI6IjI2ZmM0YzE0LTQwODctNDJiZC1hNjAwLTU2MzhmMmJhMjdlNCIsIm1lbnVfYWRtaW4iOlt7Im5hbWUiOiJEYXNoYm9hcmQiLCJpY29uIjoiZGFzaGJvYXJkIiwidXJsIjoiXC9kYXNoYm9hcmQifSx7Im5hbWUiOiJDYW1wYWlnbiIsImljb24iOiJ0aWNrZXQiLCJ1cmwiOiJcL2NhbXBhaWduIn1dfV0sInBob25lIjoiMDk4NzY1NDMyMSIsInByb3ZpZGVyIjpudWxsLCJwcm92aWRlcl9pZCI6bnVsbCwiYWN0aXZlIjoxLCJjcmVhdGVkX2F0Ijp7ImRhdGUiOiIyMDE4LTAxLTE4IDEwOjE3OjMyLjAwMDAwMCIsInRpbWV6b25lX3R5cGUiOjMsInRpbWV6b25lIjoiQXNpYVwvSmFrYXJ0YSJ9LCJ1cGRhdGVkX2F0Ijp7ImRhdGUiOiIyMDE4LTAxLTI0IDEwOjE4OjMxLjAwMDAwMCIsInRpbWV6b25lX3R5cGUiOjMsInRpbWV6b25lIjoiQXNpYVwvSmFrYXJ0YSJ9fX0=","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMzUuMTg2LjE0Ni4xNC9hcGkvdjEvYXV0aCIsImlhdCI6MTUyMTQyODk4MywiZXhwIjoxNTIxNDMyNTgzLCJuYmYiOjE1MjE0Mjg5ODMsImp0aSI6IlpLUDJ5dllxYWVCOG1pSjQiLCJzdWIiOjEsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.5WCdk1DjACMeUMdJIuMRJODjivv2wVT34eycPN4HBxI",1,1).subscribe(testSubscriber)

//        testSubscriber.assertErrorMessage("HTTP 400 Bad Request")
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)
    }



}