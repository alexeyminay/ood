package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.common.AppFrame

class ClearSelectionShapeCommand(
    private val shapes: List<IAppShape>,
    private val shapeSelectionModel: ShapeSelectionModel
) : ICommand {

    override fun execute() {
        shapeSelectionModel.clearSelection()
    }

    override fun unexecute() {
        shapeSelectionModel.setSelection(shapes)
    }

}