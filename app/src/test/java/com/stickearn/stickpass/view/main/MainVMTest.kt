package com.stickearn.stickpass.view.main

import android.util.Log
import com.stickearn.stickpass.base.BaseObserver
import com.stickearn.stickpass.data.main_repo.MainRepository
import com.stickearn.stickpass.model.BankMdl
import com.stickearn.stickpass.model.BaseMdl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations


/**
 * Created by oohyugi on 1/17/18.
 */
class MainVMTest{

    private lateinit var mainVm: MainPresenter
    lateinit var mMainRequest: MainRepository
    @Before
    fun setup(){
//        mainVm = MainPresenter()
        mMainRequest = MainRepository()
        MockitoAnnotations.initMocks(mainVm)
        MockitoAnnotations.initMocks(mMainRequest)

    }

    @Test
    fun getBank(){

        mMainRequest.loadBank(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<BaseMdl<List<BankMdl>>>() {
                    override fun onResponseError(s: String) {
                        Log.e("error", s)

                    }

                    override fun onResponseSuccess(t: BaseMdl<List<BankMdl>>) {
                        Log.wtf("data", t.message)
                        assert(true)
                    }

                })
    }
}