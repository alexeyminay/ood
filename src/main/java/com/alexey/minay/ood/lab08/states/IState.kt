package com.alexey.minay.ood.lab08.states

interface IState {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun dispense()
}