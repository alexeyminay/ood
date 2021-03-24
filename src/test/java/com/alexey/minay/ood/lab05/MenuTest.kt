package com.alexey.minay.ood.lab05

import org.junit.After
import org.junit.Assert.*
import org.junit.Test

class MenuTest {

    private var mMenu = Menu()

    @Test
    fun shouldExecuteCommand() {
        val shortcut = "shortcut"
        val description = "description"
        val params = "params"
        val command = "$shortcut $params"
        var isExecuted = false
        mMenu.addItem(shortcut, description) {
            isExecuted = true
        }
        mMenu.run(::println) {
            return@run command.also {
                mMenu.exit()
            }
        }
        assert(isExecuted)
    }

    @Test
    fun shouldExecuteCommandWithParam() {
        val shortcut = "shortcut"
        val description = "description"
        val params = "params"
        val command = "$shortcut $params"
        mMenu.addItem(shortcut, description) {
            assertEquals(command, "${it[0]} ${it[1]}")
        }
        mMenu.run(::println) {
            return@run command.also {
                mMenu.exit()
            }
        }
    }

    @Test
    fun shouldPrintUnknownCommandIfCommandDidNotAdd() {
        val shortcut = "shortcut"
        val params = "params"
        val command = "$shortcut $params"
        val errorMessage = "Unknown command"
        var isPrintedError = false
        fun print(message: String) {
            assertEquals(errorMessage, message)
            isPrintedError = true
        }
        mMenu.run(::print) {
            return@run command.also {
                mMenu.exit()
            }
        }
        assert(isPrintedError)
    }

    @After
    fun clear() {
        mMenu = Menu()
    }

}