package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.DrawableFrame
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.common.AppFrame

class SelectShapeCommand(
    private val frames: List<AppFrame>,
    private val shapeSelectionModel: ShapeSelectionModel
) : ICommand {

    override fun execute() {
        val drawableFrames = frames.map(::DrawableFrame)
        shapeSelectionModel.setSelection(drawableFrames)
    }

    override fun unexecute() {
        shapeSelectionModel.clearSelection()
    }

}