package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.domainshapes.Shape
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ApplicationDocument(
    private val document: Document,
    private val selectionModel: ShapeSelectionModel
) {

    val shapesObservable: Observable<List<IDrawable>>
        get() = Observable.combineLatest(
            selectionModel.selectedShapes, mAppShapesState, { selections, shapes ->
                mutableListOf<IDrawable>().apply {
                    addAll(selections)
                    addAll(shapes)
                }
            }
        )

    private val mAppShapesState = BehaviorSubject.create<List<IAppShape>>()

    init {
        document.subscribe { mAppShapesState.onNext(it.map(Shape::asAppShape)) }
    }

    val shapeCount: Int
        get() = document.getShapeCount()

    fun getShape(index: Int) = document.getShape(index).asAppShape()

    fun insertShapeAt(index: Int, shape: IAppShape) {
        document.insertShapeAt(index, shape.asDomainShape())
    }

    fun removeShapeAt(index: Int): IAppShape = document.removeShapeAt(index).asAppShape()

}