package com.stickearn.stickpass.data.main_repo

import com.stickearn.stickpass.R
import com.stickearn.stickpass.model.MenuMdl
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import io.reactivex.subscribers.TestSubscriber
import org.mockito.Mock
import org.mockito.Mockito.`when`


/**
 * Created by oohyugi on 2/12/18.
 */
class MainRepositoryTest {
    @Mock
    private var mRequest: MainRequest? = null
    @Mock
    private var mRepo: MainRepository? = null

    private lateinit var mTestSubscriber:TestSubscriber<List<MenuMdl>>
    private val TASKS = getDummyMenu()
    @Test
    fun loadMenu() {
        mRepo = MainRepository()
        val subscriber = TestSubscriber.create<List<MenuMdl>>()
//        setTasksAvailable(mRequest, TASKS)
        mTestSubscriber = TestSubscriber()

        subscriber.assertNoErrors()
        subscriber.assertComplete()
//        Mockito.verify(getDummyMenu())



    }



    private fun getDummyMenu(): List<MenuMdl>? {
        val  mList= mutableListOf<MenuMdl>()

        mList.add(MenuMdl(1, R.drawable.ic_checked_note, "Stick Survey"))
        mList.add(MenuMdl(2, R.drawable.ic_cart, "Stick Mart"))
        mList.add(MenuMdl(3, R.drawable.ic_help_button, "FAQ"))
        mList.add(MenuMdl(4, R.drawable.ic_infoo, "Privacy Policy"))
        return mList
    }

}