package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ShapeSelectionModel {

    val selectedShapes: Observable<List<DrawableFrame>>
        get() = mSelectedShapes
    private val mSelectedShapes = BehaviorSubject.createDefault<List<DrawableFrame>>(mutableListOf())

    fun setSelection(frames: List<AppFrame>) {
        mSelectedShapes.onNext(frames.map(::DrawableFrame))
    }

    fun clearSelection() {
        mSelectedShapes.onNext(emptyList())
    }

}