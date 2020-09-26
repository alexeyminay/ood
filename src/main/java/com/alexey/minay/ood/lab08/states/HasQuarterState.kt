package com.alexey.minay.ood.lab08.states

import com.alexey.minay.ood.lab08.IStateHandler

class HasQuarterState(
        private val stateHandler: IStateHandler
) : IState {

    override fun insertQuarter() {
        println("You inserted a quarter\n")
        stateHandler.quarterCount++
        if (stateHandler.quarterCount < 5) {
            stateHandler.setHasQuarterState()
        } else {
            stateHandler.setFullCoinAcceptorState()
        }
    }

    override fun ejectQuarter() {
        println("Quarters returned\n")
        stateHandler.setNoQuarterState()
    }

    override fun turnCrank() {
        println("You turned...\n")
        stateHandler.setSoldState()
    }

    override fun dispense() {
        println("No gumball dispensed\n")
    }

    override fun toString(): String {
        return "waiting for turn of crank"
    }

}