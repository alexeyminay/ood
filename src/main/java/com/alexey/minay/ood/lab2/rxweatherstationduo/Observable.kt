package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class DisplayObservable {

    protected var disposables: CompositeDisposable = CompositeDisposable()

    fun subscribe(valueType: ValueType, isInsideSensor: Boolean) {
        when {
            isInsideSensor -> subscribeInsideSensor(valueType)
            else -> subscribeOutsideSensor(valueType)
        }
    }

    protected abstract fun subscribeOutsideSensor(valueType: ValueType)

    protected abstract fun subscribeInsideSensor(valueType: ValueType)

    fun unSubscribe() {
        disposables.dispose()
    }

}

