package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class DisplayObserver {

    protected var disposables: CompositeDisposable = CompositeDisposable()

    fun observe(valueType: ValueType, isInsideSensor: Boolean) {
        when {
            isInsideSensor -> observeInsideSensor(valueType)
            else -> observeOutsideSensor(valueType)
        }
    }

    protected abstract fun observeOutsideSensor(valueType: ValueType)

    protected abstract fun observeInsideSensor(valueType: ValueType)

    fun dispose() {
        disposables.dispose()
    }

}

