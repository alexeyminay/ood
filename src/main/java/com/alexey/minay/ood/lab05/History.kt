package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.commands.ICommand

class History {

    private var mCursor = 0
    private val mCommands = mutableListOf<ICommand>()
    private val mCanUndo: Boolean
        get() = mCommands.isNotEmpty() && mCursor != 0
    private val mCanRedo: Boolean
        get() = mCursor < mCommands.size

    fun addAnExecute(command: ICommand) {
        if (mCanRedo) {
            for (i in mCommands.lastIndex downTo mCursor) {
                mCommands.removeAt(i).also {
                    it.onClear()
                }
            }
        }

        mCommands.add(command)

        if (mCommands.size > MAX_SAVED_COMMAND) {
            mCommands.removeAt(FIRST_SAVED_COMMAND_INDEX)
        }

        if (mCursor < 10) mCursor++
        command.execute()
    }

    fun undo() {
        if (!mCanUndo) throw RuntimeException("Can't undo")
        mCursor--
        mCommands[mCursor].unExecute()
    }

    fun redo() {
        if (!mCanRedo) throw RuntimeException("Can't redo")
        mCommands[mCursor].execute()
        mCursor++
    }

    fun clearHistory() {
        mCommands.forEach { it.onClear() }
    }

    companion object {
        private const val MAX_SAVED_COMMAND = 10
        private const val FIRST_SAVED_COMMAND_INDEX = 0
    }

}