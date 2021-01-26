package com.alexey.minay.ood.lab08.states

import com.alexey.minay.ood.lab08.IState
import com.alexey.minay.ood.lab08.IStateHandler

class NoQuarterState(
        private val stateHandler: IStateHandler
) : IState {

    override fun insertQuarter() {
        println("You inserted a quarter\n")
        stateHandler.quarterCount++
        stateHandler.setHasQuarterState()
    }

    override fun ejectQuarter() {
        println("You haven't inserted a quarter\n")
    }

    override fun turnCrank() {
        println("You turned but there's no quarter\n")
    }

    override fun dispense() {
        println("You need to pay first\n")
    }

    override fun toString(): String {
        return "waiting for quarter"
    }

}