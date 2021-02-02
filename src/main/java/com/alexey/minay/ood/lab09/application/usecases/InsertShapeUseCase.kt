package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.CanvasAppModel
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.commands.InsertShapeCommand
import com.alexey.minay.ood.lab09.domain.shapes.Point

class InsertShapeUseCase(
    private val canvasAppModel: CanvasAppModel,
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
                insertPoint = Point(insertWidth / 2, insetHeight / 2)
            )
        )
    }

}