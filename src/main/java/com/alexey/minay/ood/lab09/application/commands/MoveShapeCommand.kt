package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand

class MoveShapeCommand(
    private val document: ApplicationDocument,
    private val changedShapes: List<IAppShape>
): ICommand {

    override fun execute() {
        TODO("Not yet implemented")
    }

    override fun unexecute() {
        TODO("Not yet implemented")
    }
}