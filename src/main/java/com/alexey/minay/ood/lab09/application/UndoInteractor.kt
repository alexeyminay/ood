package com.alexey.minay.ood.lab09.application

class UndoInteractor(
    private val history: CommandHistory
) {

    fun undo() {
        history.undo()
    }

    fun redo() {
        history.redo()
    }


}