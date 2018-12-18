package com.stickearn.stickpass.data.policy

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.data.point_repo.PointRepository
import com.stickearn.stickpass.data.point_repo.PointRequest
import com.stickearn.stickpass.model.PointResponseMdl
import com.stickearn.stickpass.model.PolicyMdl
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/19/18.
 */
class PolicyRepositoryTest{
    @Mock
    lateinit var mRequest: PolicyRequest

    @Before
    fun setup(){
        mRequest = Mockito.mock(PolicyRequest::class.java)
        CustomSchedulers()

    }
    @Test
    fun getPolicy() {
        mRequest = PolicyRepository()
        val testSubscriber =  TestObserver<PolicyMdl>()
        mRequest.getPolicy("eyJhY2NvdW50Ijp7ImlkIjo5MywibmFtZSI6Iklyd2FuIGR1YSIsImVtYWlsIjoiaXJ3YW4yQHRlc3QuY29tIiwiYWNjZXNzIjpbeyJncm91cCI6ImRyaXZlciIsInV1aWRfZHJpdmVyIjoiNmEzNzM1NmMtYTQ1ZS00N2M3LTgzYjQtODA4YjI3M2YzMGE4IiwibWVudV9kcml2ZXIiOltdfV0sInBob25lIjoiMDk4MjkxMjkzMTIxMTIiLCJwcm92aWRlciI6bnVsbCwicHJvdmlkZXJfaWQiOm51bGwsImFjdGl2ZSI6MSwiY3JlYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMy0xMyAxNDo0NTo1NS4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifSwidXBkYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMy0xMyAxNDo0NTo1NS4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifX19","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMzUuMTg2LjE0Ni4xNC9hcGkvdjEvYXV0aCIsImlhdCI6MTUyMTQzMjcxMCwiZXhwIjoxNTIxNDM2MzEwLCJuYmYiOjE1MjE0MzI3MTAsImp0aSI6IkhLQ2VnbFdZY2lxQTQ2eE0iLCJzdWIiOjkzLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.Nq3RYzMMBp-m6CN_F4B_Q-lFvheW3uPYwFJTXpc3RZI",1)
                .subscribe(testSubscriber)
    }

}