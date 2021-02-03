package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.IAppShape

class SelectShapeCommand(
    private val shape: IAppShape
): ICommand {

    override fun execute() {
        shape.isSelected = true
    }

    override fun unexecute() {
        shape.isSelected = false
    }
}