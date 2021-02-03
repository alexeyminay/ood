package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.domainshapes.Shape
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class Document {

    val state: Observable<MutableList<Shape>>
        get() = mState
    private val mState = BehaviorSubject.createDefault(mutableListOf<Shape>())

    fun getShapeCount() = mState.value.size

    fun getShape(index: Int): Shape = mState.value[index]

    fun insertShapeAt(index: Int, shape: Shape) {
        mState.onNext(mState.value.apply { add(index, shape) })
    }

    fun removeShapeAt(index: Int) = mState.value.removeAt(index)

}