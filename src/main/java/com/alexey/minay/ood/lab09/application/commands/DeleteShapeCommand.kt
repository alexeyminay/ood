package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.IAppShape

class DeleteShapeCommand(
    private val model: ApplicationDocument,
    private val deletePosition: Int,
) : ICommand {

    private val mDeletedShape: IAppShape? = null

    override fun execute() {
        model.removeShapeAt(deletePosition)
    }

    override fun unexecute() {
        mDeletedShape?.let { model.insertShapeAt(deletePosition, it) }
    }

}