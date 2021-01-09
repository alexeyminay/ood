package com.alexey.minay.ood.lab05.commands

interface ICommand {
    fun execute()
    fun unExecute()
    fun onClear()
}