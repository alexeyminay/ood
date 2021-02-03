package com.alexey.minay.ood.lab09.application

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ShapeSelectionModel {

    val selectedShapes: Observable<List<DrawableFrame>>
        get() = mSelectedShapes
    private val mSelectedShapes = BehaviorSubject.create<List<DrawableFrame>>()

    fun setSelection(selections: List<DrawableFrame>) {
        mSelectedShapes.onNext(selections)
    }

    fun clearSelection() {
        mSelectedShapes.onNext(emptyList())
    }

}