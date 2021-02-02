package com.alexey.minay.ood.lab09.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class Document {

    val state: Observable<MutableList<IShape>>
        get() = mState
    private val mState = BehaviorSubject.createDefault(mutableListOf<IShape>())

    fun getShapeCount() = mState.value.size

    fun getShape(index: Int): IShape = mState.value[index]

    fun insertShapeAt(index: Int, shape: IShape) {
        mState.onNext(mState.value.apply { add(index, shape) })
    }

    fun removeShapeAt(index: Int) = mState.value.removeAt(index)

}