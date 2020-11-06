package com.alexey.minay.ood.lab08.states

import com.alexey.minay.ood.lab08.IStateHandler

class FullCoinAcceptorState(
        private val stateHandler: IStateHandler
) : IState {

    override fun insertQuarter() {
        println("You can't insert one more quarter\n")
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