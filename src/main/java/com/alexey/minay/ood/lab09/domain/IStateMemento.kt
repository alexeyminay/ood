package com.alexey.minay.ood.lab09.domain

interface IStateMemento {
    fun undo()
    fun redo()
    fun saveState()
    fun canUndo(): Boolean
    fun canRedo(): Boolean
    fun deleteSnapshots()
}