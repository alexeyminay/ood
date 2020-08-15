package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.commands.ICommand


class Receiver {

    fun add(command: ICommand) {
        command.execute()
    }

}