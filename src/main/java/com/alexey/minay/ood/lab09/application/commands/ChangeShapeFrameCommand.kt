package com.alexey.minay.ood.lab09.application.commands

import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICommand
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.common.asAppShape
import com.alexey.minay.ood.lab09.application.common.asDomainFrame

class ChangeShapeFrameCommand(
    private val documentAdapter: DocumentAdapter,
    private val changedShapes: List<IAppShape>,
    private val selectionModel: ShapeSelectionModel
) : ICommand {

    private val mOldShapes = mutableListOf<IAppShape>()
    private val mNewShapes = mutableListOf<IAppShape>()

    override fun execute() {
        if (mNewShapes.isEmpty()) {
            for (shape in changedShapes) {
                val oldShape = documentAdapter.getFramesByUid(shape.uid) ?: continue
                mOldShapes.add(oldShape.copy().asAppShape())
                oldShape.update(shape.frame.asDomainFrame())
            }
            selectionModel.setSelection(changedShapes)
        } else {
            for (shape in mNewShapes) {
                val oldShape = documentAdapter.getFramesByUid(shape.uid) ?: continue
                oldShape.update(shape.frame.asDomainFrame())
            }
            selectionModel.setSelection(mNewShapes)
        }
        documentAdapter.observeAll()
    }

    override fun unexecute() {
        for (shape in mOldShapes) {
            val newShape = documentAdapter.getFramesByUid(shape.uid) ?: continue
            mNewShapes.add(newShape.copy().asAppShape())
            newShape.update(shape.frame.asDomainFrame())
        }
        selectionModel.setSelection(mOldShapes)
        documentAdapter.observeAll()
    }

}