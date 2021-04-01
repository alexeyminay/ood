package com.alexey.minay.ood.lab09.application

class CommandHistory {

    private var mCursor = 0
    private val mCommands = mutableListOf<ICommand>()
    private val mCanUndo: Boolean
        get() = mCommands.isNotEmpty() && mCursor != 0
    private val mCanRedo: Boolean
        get() = mCursor < mCommands.size

    fun addAnExecute(command: ICommand) {
        if (mCanRedo) {
            for (i in mCommands.lastIndex downTo mCursor) {
                mCommands.removeAt(i)
            }
        }

        mCommands.add(command)

        if (mCommands.size > MAX_SAVED_COMMAND) {
            mCommands.removeAt(FIRST_SAVED_COMMAND_INDEX)
        }
        if (mCursor < MAX_SAVED_COMMAND) mCursor++
        command.execute()
    }

    fun undo() {
        if (!mCanUndo) return
        mCursor--
        mCommands[mCursor].unexecute()
    }

    fun redo() {
        if (!mCanRedo) return
        mCommands[mCursor].execute()
        mCursor++
    }

    companion object {
        private const val MAX_SAVED_COMMAND = 10
        private const val FIRST_SAVED_COMMAND_INDEX = 0
    }

}