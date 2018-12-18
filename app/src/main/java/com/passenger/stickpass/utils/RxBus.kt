package com.stickearn.stickpass.utils

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscription

/**
 * Created by oohyugi on 3/26/18.
 */
enum class RxBus {
    INSTANCE;

    private val bus = PublishSubject.create<String>() // the actual publisher handling all of the events

    fun send(message: String) {
        bus.onNext(message) // the message being sent to all subscribers
    }

    fun toObserverable(): Observable<String> {
        return bus // return the publisher itself as an observable to subscribe to
    }
}