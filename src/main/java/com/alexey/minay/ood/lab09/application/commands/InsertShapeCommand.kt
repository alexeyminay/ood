package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.CanvasAppModel
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.domain.shapes.Ellipse
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.shapes.Rectangle
import com.alexey.minay.ood.lab09.domain.shapes.Triangle

class InsertShapeCommand(
    private val model: CanvasAppModel,
    private val shapeType: ShapeType,
    private val insertPoint: Point,
) : ICommand {

    override fun execute() {
        val shape = when (shapeType) {
            ShapeType.ELLIPSE ->
                Ellipse.createDefault(insertPoint)
            ShapeType.RECTANGLE ->
                Rectangle.createDefault(insertPoint)
            ShapeType.TRIANGLE ->
                Triangle.createDefault(insertPoint)
        }
        model.insertShapeAt(model.shapeCount, shape)
    }

    override fun unexecute() {
        model.removeShapeAt(model.shapeCount)
    }
}