package com.alexey.minay.ood.lab08

class Menu {

    private val mItems = mutableMapOf<String, Item>()
    private var mExit = false

    fun addItem(shortcut: String, description: String, command: (String) -> Unit) {
        mItems[shortcut] = Item(description, command)
    }

    fun run(message: (String) -> Unit) {
        while (!mExit) {
            val input = readLine() ?: continue
            input.split(" ").also {
                mItems[it[0]]?.command
                        ?.invoke(input)
                        ?: message("Unknown command")
            }
        }
    }

    fun showInstructions() {
        mItems.forEach {
            println("- ${it.key} - ${it.value.description}")
        }
    }

    fun exit() {
        mExit = true
    }

    private inner class Item(
            val description: String,
            val command: (String) -> Unit
    )

}