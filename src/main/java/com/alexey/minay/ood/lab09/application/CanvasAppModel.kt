package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler

class CanvasAppModel(
    private val stateHandler: ImageStateHandler
) {

    val shapeCount: Int
        get() = stateHandler.getShapeCount()

    fun getShape(index: Int) = ShapeAppModel(stateHandler.getShape(index))

}