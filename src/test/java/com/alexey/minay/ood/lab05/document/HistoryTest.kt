package com.alexey.minay.ood.lab05.document

import com.alexey.minay.ood.lab05.ICommand
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class HistoryTest {

    @Test
    fun shouldExecuteAfterAdding() {
        val history = History()
        val command = mock<ICommand>()
        history.addAnExecute(command)
        verify(command).execute()
    }

    @Test
    fun shouldUnExecuteAfterUndo() {
        val history = History()
        val command = mock<ICommand>()
        history.addAnExecute(command)
        history.undo()
        verify(command).execute()
        verify(command).unExecute()
    }

    @Test
    fun shouldExecuteAfterRedo() {
        val history = History()
        val command = mock<ICommand>()
        history.addAnExecute(command)
        history.undo()
        verify(command).execute()
        verify(command).unExecute()
        verify(command).execute()
    }

    @Test
    fun shouldClearHistory() {
        val history = History()
        val command = mock<ICommand>()
        history.addAnExecute(command)
        history.clearHistory()
        verify(command).onClear()
    }

}