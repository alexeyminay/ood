package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppFrame

class ChangeFrameCommand(
    private val shape: IAppShape,
    private val newFrame: AppFrame
) : ICommand {

    private val oldFrame: AppFrame = shape.frame

    override fun execute() {
        shape.frame = newFrame
    }

    override fun unexecute() {
        shape.frame = oldFrame
    }

}