package com.stickearn.stickpass.view.register

import org.junit.Test

import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Created by oohyugi on 2/9/18.
 */
class RegisterPresenterTest {




    @Test
    fun validateRegisterErrorEmail() {
//        val mView:RegisterView = mock(RegisterView::class.java)
        val mView= Mockito.mock(RegisterView::class.java)
        val presenter = RegisterPresenter(mView)
        presenter.validateRegister("yogi","yogi.gmail.com","00888984545363","123456","123456")
//        verify(mView).errorValidate("Format email salah")
//        verify(mView).errorValidate("Password to sort")


    }

    @Test
    fun validateRegisterErrorPhone() {
//        val mView:RegisterView = mock(RegisterView::class.java)
        val mView= Mockito.mock(RegisterView::class.java)
        val presenter = RegisterPresenter(mView)
        presenter.validateRegister("yogi","yogi.gmail.com","00888","123456","123456")
        // verify(mView).errorValidate("Format email salah")
//        verify(mView).errorValidate("Phone to sort")


    }

}
