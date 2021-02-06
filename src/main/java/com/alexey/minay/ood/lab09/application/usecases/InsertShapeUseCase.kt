package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.commands.InsertShapeCommand
import com.alexey.minay.ood.lab09.application.commands.SelectMacroCommand
import com.alexey.minay.ood.lab09.application.commands.SelectShapeCommand
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle

class InsertShapeUseCase(
    private val document: DocumentAdapter,
    private val history: CommandHistory,
    private val shapeSelectionModel: ShapeSelectionModel
) {

    operator fun invoke(
        shapeType: ShapeType,
        parentWidth: Double,
        parentHeight: Double
    ) {
        val frame = AppFrame(
            leftTop = AppPoint(parentWidth / 2 - FRAME_HALF_SIZE, parentHeight / 2 - FRAME_HALF_SIZE),
            rightBottom = AppPoint(parentWidth / 2 + FRAME_HALF_SIZE, parentHeight / 2 + FRAME_HALF_SIZE)
        )
        val uid = System.currentTimeMillis()
        val shape = when (shapeType) {
            ShapeType.ELLIPSE ->
                Ellipse(frame, uid)
            ShapeType.RECTANGLE ->
                Rectangle(frame, uid)
            ShapeType.TRIANGLE ->
                Triangle(frame, uid)
        }
        val insertCommand = InsertShapeCommand(
            model = document,
            shape = shape
        )
        history.addAnExecute(
            command = SelectMacroCommand(
                targetCommand = insertCommand,
                selectShapeCommand = SelectShapeCommand(
                    mutableListOf(shape),
                    shapeSelectionModel
                )
            )
        )
    }

    companion object {

        private const val FRAME_HALF_SIZE = 50

    }

}