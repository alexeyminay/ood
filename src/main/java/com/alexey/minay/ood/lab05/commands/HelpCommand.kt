package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.Help

class HelpCommand: ICommand{

    override fun execute() {
        Help.print()
    }

}