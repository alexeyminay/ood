package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.Title
import org.junit.Test
import kotlin.test.assertEquals

class SetTitleCommandTest {

    @Test
    fun shouldSetTitle() {
        val oldTitle = "old"
        val target = Title(oldTitle)
        val newTitle = "title"
        SetTitleCommand(target, newTitle).execute()
        assertEquals(newTitle, target.data)
    }

    @Test
    fun shouldUnExecute() {
        val oldTitle = "old"
        val target = Title(oldTitle)
        val newTitle = "title"
        val command = SetTitleCommand(target, newTitle)
        command.execute()
        command.unExecute()
        assertEquals(oldTitle, target.data)
    }

}