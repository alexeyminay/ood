package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ICommand

class SelectMacroCommand(
    private val targetCommand: ICommand,
    private val selectShapeCommand: ICommand
): ICommand {

    override fun execute() {
        targetCommand.execute()
        selectShapeCommand.execute()
    }

    override fun unexecute() {
        targetCommand.unexecute()
        selectShapeCommand.unexecute()
    }

}