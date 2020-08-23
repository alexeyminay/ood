package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


interface IObservable<T> {
    val observable: Observable<T>
}

abstract class Observer<T> : Observer<T> {

    override fun onComplete() {
        println("finish observing")
    }

    override fun onSubscribe(d: Disposable?) {
        println("start observing")
    }

    override fun onError(e: Throwable?) {
        println(e?.localizedMessage)
    }

}

