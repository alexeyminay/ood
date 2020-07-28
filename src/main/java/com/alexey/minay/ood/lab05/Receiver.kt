package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.commands.ICommand


class Receiver {

//    private val commands = mutableListOf<ICommand>()

    fun add(command: ICommand) {
        command.execute()
    }

//    fun run(){
//        commands.forEach { it.execute() }
//    }
}