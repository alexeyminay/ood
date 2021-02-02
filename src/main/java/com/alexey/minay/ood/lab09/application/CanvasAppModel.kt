package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.IShape
import io.reactivex.rxjava3.core.Observable

class CanvasAppModel(
    private val stateHandler: Document
) {

    val shapesObservable: Observable<List<ShapeAppModel>>
        get() = stateHandler.state.map {
            it.map { shape ->
                shape.asShapeAppModel()
            }
        }

    val shapeCount: Int
        get() = stateHandler.getShapeCount()

    fun getShape(index: Int) = stateHandler.getShape(index).asShapeAppModel()

    fun insertShapeAt(index: Int, shape: IShape) {
        stateHandler.insertShapeAt(index, shape)
    }

    fun removeShapeAt(index: Int): IShape = stateHandler.removeShapeAt(index)

    private fun IShape.asShapeAppModel() =
        ShapeAppModel(this)

}