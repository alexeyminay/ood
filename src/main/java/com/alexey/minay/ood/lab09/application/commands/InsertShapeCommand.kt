package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle

class InsertShapeCommand(
    private val model: ApplicationDocument,
    private val shapeType: ShapeType,
    private val insertPoint: AppPoint,
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