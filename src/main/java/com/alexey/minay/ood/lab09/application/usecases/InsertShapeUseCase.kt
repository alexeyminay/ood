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
    private val canvasAppModel: ApplicationDocument,
    private val history: CommandHistory,
    private val shapeSelectionModel: ShapeSelectionModel
) {

    operator fun invoke(
        shapeType: ShapeType,
        parentWidth: Double,
        parentHeight: Double
    ) {
        val insertCommand = InsertShapeCommand(
            model = canvasAppModel,
            shapeType = shapeType,
            insertPoint = AppPoint(parentWidth / 2, parentHeight / 2)
        )
        history.addAnExecute(
            command = SelectMacroCommand(
                targetCommand = insertCommand,
                selectShapeCommand =
                SelectShapeCommand(
                    mutableListOf(AppFrame(AppPoint(20.0, 30.0), AppPoint(100.0, 100.0))),
                    shapeSelectionModel
                )
            )
        )
    }

}