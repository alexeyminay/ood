package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.IShape
import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CanvasAppModel(
    private val stateHandler: ImageStateHandler
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