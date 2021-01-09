package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.Title

class SetTitleCommand(
        private val targetTitle: Title,
        private val newTitle: String
) : AbstractCommand() {

    private var mOldTitle: String = ""

    override fun doExecute() {
        mOldTitle = targetTitle.data
        targetTitle.data = newTitle
    }

    override fun doUnExecute() {
        targetTitle.data = mOldTitle
    }

}