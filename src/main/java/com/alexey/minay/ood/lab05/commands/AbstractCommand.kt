package com.alexey.minay.ood.lab05.commands

abstract class AbstractCommand : ICommand {

    private var mExecuted: Boolean = false

    override fun execute() {
        if (!mExecuted) {
            doExecute()
            mExecuted = true
        }
    }

    override fun unExecute() {
        if (mExecuted) {
            doUnExecute()
            mExecuted = false
        }
    }

    override fun onClear() {}

    protected abstract fun doExecute()
    protected abstract fun doUnExecute()

}