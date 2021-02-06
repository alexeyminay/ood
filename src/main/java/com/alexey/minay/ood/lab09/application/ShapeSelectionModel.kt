package com.alexey.minay.ood.lab09.application

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ShapeSelectionModel() {

    val selections: Observable<List<DrawableFrame>>
        get() = mSelections
    private val mSelections = BehaviorSubject.createDefault<List<DrawableFrame>>(mutableListOf())
    private val mSelectedShapeUids = mutableListOf<Long>()

    fun setSelection(shapes: List<IAppShape>) {
        mSelectedShapeUids.clear()
        mSelectedShapeUids.addAll(shapes.map(IAppShape::uid))
        mSelections.onNext(shapes.map { DrawableFrame(it.frame) })
    }

    fun getSelectionShapeUids() = mSelectedShapeUids

    fun clearSelection() {
        mSelectedShapeUids.clear()
        mSelections.onNext(emptyList())
    }

}