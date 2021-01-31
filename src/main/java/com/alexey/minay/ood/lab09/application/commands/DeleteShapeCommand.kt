package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.CanvasAppModel
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.domain.IShape

class DeleteShapeCommand(
    private val model: CanvasAppModel,
    private val deletePosition: Int,
) : ICommand {

    private val mDeletedShape: IShape? = null

    override fun execute() {
        model.removeShapeAt(deletePosition)
    }

    override fun unexecute() {
        mDeletedShape?.let { model.insertShapeAt(deletePosition, it) }
    }

}