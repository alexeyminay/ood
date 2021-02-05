package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.domainshapes.Shape
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ApplicationDocument(
    private val document: Document,
    private val selectionModel: ShapeSelectionModel
) {

    val shapeCount: Int
        get() = document.getShapeCount()

    val shapesObservable: Observable<List<IDrawable>>
        get() = Observable.combineLatest(
            selectionModel.selectedShapes, mAppShapesState, { selections, shapes ->
                mutableListOf<IDrawable>().apply {
                    addAll(selections)
                    addAll(shapes)
                }
            }
        )

    private val mAppShapesState = BehaviorSubject.createDefault<List<IAppShape>>(mutableListOf())

    init {
        document.subscribe { mAppShapesState.onNext(it.map(Shape::asAppShape)) }
    }

    fun getShapeContains(point: AppPoint) = mAppShapesState.value.findLast { it.isIncluding(point) }

    fun insertShapeAt(index: Int, shape: IAppShape) {
        document.insertShapeAt(index, shape.asDomainShape())
    }

    fun removeShapeAt(index: Int): IAppShape = document.removeShapeAt(index).asAppShape()

}