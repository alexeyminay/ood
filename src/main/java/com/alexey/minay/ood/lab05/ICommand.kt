package com.alexey.minay.ood.lab05

interface ICommand {
    fun execute()
    fun unExecute()
    fun onClear()
}