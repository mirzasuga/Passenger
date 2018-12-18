package com.stickearn.stickpass.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper.isDisposed
import io.reactivex.internal.disposables.DisposableHelper.isDisposed




/**
 * Created by oohyugi on 1/17/18.
 */
class DisposableManager {

    private var compositeDisposable: CompositeDisposable? = null
//    private var compositeDisposable: MutableList<Disposable> = mutableListOf()
    fun add(disposable: Disposable) {

            getCompositeDisposable().add(disposable)


    }

    fun dispose() {
       getCompositeDisposable().dispose()

    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed()) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable as CompositeDisposable
    }




}