package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.domain.domainshapes.Shape

class MoveShapeCommand(
    private val documentAdapter: DocumentAdapter,
    private val changedShapes: List<IAppShape>
) : ICommand {

    private val mOldFrames = mutableListOf<Shape>()

    override fun execute() {
        for (shape in changedShapes) {
            val oldShape = documentAdapter.getFramesByUid(shape.uid) ?: continue
            mOldFrames.add(oldShape.copy())
            oldShape.frame.leftTop.x = shape.frame.leftTop.x
            oldShape.frame.leftTop.y = shape.frame.leftTop.y
            oldShape.frame.rightBottom.x = shape.frame.rightBottom.x
            oldShape.frame.rightBottom.y = shape.frame.rightBottom.y
        }
        documentAdapter.observeAll()
    }

    override fun unexecute() {
        TODO("Not yet implemented")
    }

}