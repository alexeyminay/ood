package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.domain.IShape
import com.alexey.minay.ood.lab09.domain.shapes.Frame

class ChangeFrameCommand(
    private val shape: IShape,
    private val newFrame: Frame
) : ICommand {

    private val oldFrame: Frame = shape.frame

    override fun execute() {
        shape.frame = newFrame
    }

    override fun unexecute() {
        shape.frame = oldFrame
    }

}