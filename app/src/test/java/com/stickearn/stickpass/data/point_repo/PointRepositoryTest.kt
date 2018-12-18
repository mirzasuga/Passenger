package com.stickearn.stickpass.data.point_repo

import com.stickearn.stickpass.CustomSchedulers
import com.stickearn.stickpass.model.BaseMdl
import com.stickearn.stickpass.model.PointHistoryResponseMdl
import com.stickearn.stickpass.model.PointResponseMdl
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by oohyugi on 3/14/18.
 */
class PointRepositoryTest {
    @Mock
    lateinit var mRequest: PointRequest

    @Before
    fun setup(){
        mRequest = Mockito.mock(PointRequest::class.java)
        CustomSchedulers()

    }
    @Test
    fun getPoint() {
        mRequest = PointRepository()
      val testSubscriber =  TestObserver<PointResponseMdl>()
        mRequest.getPoint("eyJhY2NvdW50Ijp7Im9hdXRoIjogIjEzMyIsIm1hcnQiOiAiMjA5YzNhZWQtNTJhNi00NGIxLWJlMzItMzQ1OWUwODNhYjU2IiwicG9pbnRzIjogImIwZjE5OTU3LTRkMDctNDM2Ny1iYTBlLWVkNzllZGFmMmQwZCIsInN1cnZleSI6ICJhODVjOGM2OS0yNjBhLTRhODQtOGM5Zi0yZGE5ZjJkOTdiNTIiLCJlYXJuIjogIjU5ZGU5ZTYyLTM0ODEtNDUxZC04OWNhLTNkNzMxYWVjZDM1NyIsIm5hbWUiOiJEZWR5IFNldGlhd2FuIiwiZW1haWwiOiJkZWR5c0BzdGlja2Vhcm4uY29tIiwicm9sZSI6ImFkbWluIn19","123","b85ceccc-0523-45c3-8943-0f5c3adc4c68")
                .subscribe(testSubscriber)
    }

    @Test
    fun getPointHistory() {
        mRequest = PointRepository()
        val testSubscriber =  TestObserver<BaseMdl<List<PointHistoryResponseMdl>>>()
        mRequest.getPointHistory("eyJhY2NvdW50Ijp7ImlkIjo5MywibmFtZSI6Iklyd2FuIGR1YSIsImVtYWlsIjoiaXJ3YW4yQHRlc3QuY29tIiwiYWNjZXNzIjpbeyJncm91cCI6ImRyaXZlciIsInV1aWRfZHJpdmVyIjoiNmEzNzM1NmMtYTQ1ZS00N2M3LTgzYjQtODA4YjI3M2YzMGE4IiwibWVudV9kcml2ZXIiOltdfV0sInBob25lIjoiMDk4MjkxMjkzMTIxMTIiLCJwcm92aWRlciI6bnVsbCwicHJvdmlkZXJfaWQiOm51bGwsImFjdGl2ZSI6MSwiY3JlYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMy0xMyAxNDo0NTo1NS4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifSwidXBkYXRlZF9hdCI6eyJkYXRlIjoiMjAxOC0wMy0xMyAxNDo0NTo1NS4wMDAwMDAiLCJ0aW1lem9uZV90eXBlIjozLCJ0aW1lem9uZSI6IkFzaWFcL0pha2FydGEifX19","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vMzUuMTg2LjE0Ni4xNC9hcGkvdjEvYXV0aCIsImlhdCI6MTUyMTYwMDk3NiwiZXhwIjoxNTIxNjA0NTc2LCJuYmYiOjE1MjE2MDA5NzYsImp0aSI6IlVZUW51OWg5akhxc0JBMmQiLCJzdWIiOjkzLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.1GvfisQI0iqa2Y_2odjr0TSS-XIr68MlS0Tb8_5Fhbc","b85ceccc-0523-45c3-8943-0f5c3adc4c68")
                .subscribe(testSubscriber)
    }

}