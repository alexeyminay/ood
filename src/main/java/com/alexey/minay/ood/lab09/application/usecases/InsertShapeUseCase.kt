package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.commands.InsertShapeCommand
import com.alexey.minay.ood.lab09.application.common.AppPoint

class InsertShapeUseCase(
    private val canvasAppModel: ApplicationDocument,
    private val history: CommandHistory,
) {

    operator fun invoke(
        shapeType: ShapeType,
        insertWidth: Double,
        insetHeight: Double
    ) {
        history.addAnExecute(
            command = InsertShapeCommand(
                model = canvasAppModel,
                shapeType = shapeType,
                insertPoint = AppPoint(insertWidth / 2, insetHeight / 2)
            )
        )
    }

}