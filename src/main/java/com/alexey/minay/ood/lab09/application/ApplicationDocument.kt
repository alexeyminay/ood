package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.shapes.Shape
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ApplicationDocument(
    document: Document,
    private val selectionModel: ShapeSelectionModel
) {

    val shapesObservable: Observable<List<IDrawable>>
        get() = Observable.combineLatest(
            selectionModel.selections, mAppShapesState, { selections, shapes ->
                mutableListOf<IDrawable>().apply {
                    addAll(shapes)
                    addAll(selections)
                }
            }
        )

    private val mAppShapesState = BehaviorSubject.createDefault<List<IAppShape>>(mutableListOf())

    init {
        document.subscribe { mAppShapesState.onNext(it.map(Shape::asAppShape)) }
    }

    fun getShapeContains(point: AppPoint) = mAppShapesState.value.findLast { it.isIncluding(point) }

    fun getShapesBy(uids: List<Long>) = mAppShapesState.value.filter { uids.contains(it.uid) }

    fun onChanged() {
        mAppShapesState.onNext(mAppShapesState.value)
    }

}