package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle

class InsertShapeCommand(
    private val model: ApplicationDocument,
    private val shapeType: ShapeType,
    private val frame: AppFrame
) : ICommand {

    override fun execute() {
        val shape = when (shapeType) {
            ShapeType.ELLIPSE ->
                Ellipse(frame)
            ShapeType.RECTANGLE ->
                Rectangle(frame)
            ShapeType.TRIANGLE ->
                Triangle(frame)
        }
        model.insertShapeAt(model.shapeCount, shape)
    }

    override fun unexecute() {
        model.removeShapeAt(model.shapeCount)
    }
}