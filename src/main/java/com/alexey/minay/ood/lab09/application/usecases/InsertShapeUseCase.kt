package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.commands.InsertShapeCommand
import com.alexey.minay.ood.lab09.application.commands.SelectMacroCommand
import com.alexey.minay.ood.lab09.application.commands.SelectShapeCommand
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

class InsertShapeUseCase(
    private val document: ApplicationDocument,
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
        val insertCommand = InsertShapeCommand(
            model = document,
            shapeType = shapeType,
            frame = frame
        )
        history.addAnExecute(
            command = SelectMacroCommand(
                targetCommand = insertCommand,
                selectShapeCommand =
                SelectShapeCommand(
                    mutableListOf(frame),
                    shapeSelectionModel
                )
            )
        )
    }

    companion object {

        private const val FRAME_HALF_SIZE = 50

    }

}