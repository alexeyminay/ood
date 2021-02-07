package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.common.asAppShape

class DeleteShapeCommand(
    private val documentAdapter: DocumentAdapter,
    private val deletingShapesUids: List<Long>,
) : ICommand {

    private var mDeletedShapes: MutableMap<Int, IAppShape> = mutableMapOf()

    override fun execute() {
        val deletedShapes = documentAdapter.removeShapesBy(deletingShapesUids)
        deletedShapes.forEach { (index, shape) -> mDeletedShapes[index] = shape.asAppShape() }
    }

    override fun unexecute() {
        mDeletedShapes.forEach { (index, shape) ->
            documentAdapter.insertShapeAt(index, shape)
        }
    }

}