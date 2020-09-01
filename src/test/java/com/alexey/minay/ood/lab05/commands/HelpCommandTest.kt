package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.Help
import io.mockk.mockkObject
import org.junit.Test

class HelpCommandTest {

    private val mHelpCommand = HelpCommand()

    @Test
    fun shouldCloseDocument() {
        mockkObject(Help)
        mHelpCommand.execute()
        io.mockk.verify { Help.print() }
    }


}