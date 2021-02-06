package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand

class InsertShapeCommand(
    private val model: DocumentAdapter,
    private val shape: IAppShape
) : ICommand {

    override fun execute() {
        model.insertShapeAt(model.getShapeCount(), shape)
    }

    override fun unexecute() {
        model.removeShapeAt(model.getShapeCount())
    }
}