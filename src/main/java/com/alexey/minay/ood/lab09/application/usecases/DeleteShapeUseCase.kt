package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.commands.ClearSelectionShapeCommand
import com.alexey.minay.ood.lab09.application.commands.DeleteShapeCommand
import com.alexey.minay.ood.lab09.application.commands.SelectMacroCommand

class DeleteShapeUseCase(
    private val documentAdapter: DocumentAdapter,
    private val history: CommandHistory,
    private val shapeSelectionModel: ShapeSelectionModel,
    private val applicationDocument: ApplicationDocument
) {

    operator fun invoke() {
        val uids = shapeSelectionModel.getSelectionShapeUids()
        val deleteCommand = DeleteShapeCommand(
            documentAdapter = documentAdapter,
            deletingShapesUids = uids
        )
        val selectMacroCommand = SelectMacroCommand(
            targetCommand = deleteCommand,
            selectShapeCommand = ClearSelectionShapeCommand(applicationDocument.getShapesBy(uids), shapeSelectionModel)
        )
        history.addAnExecute(selectMacroCommand)
    }

}