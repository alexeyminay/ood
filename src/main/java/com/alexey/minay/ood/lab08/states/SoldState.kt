package com.alexey.minay.ood.lab08.states

import com.alexey.minay.ood.lab08.IStateHandler


class SoldState(
        private val stateHandler: IStateHandler
) : IState {

    override fun insertQuarter() {
        println("Please wait, we're already giving you a gumball\n")
        stateHandler.setHasQuarterState()
    }

    override fun ejectQuarter() {
        println("Sorry you already turned the crank\n")
    }

    override fun turnCrank() {
        println("Turning twice doesn't get you another gumball\n")
    }

    override fun dispense() {
        stateHandler.releaseBall()
        if (stateHandler.getBallCount() == 0) {
            println("Oops, out of gumballs\n")
            println("Quarters returned\n")
            stateHandler.setSoldOutState()
        } else {
            stateHandler.quarterCount--
            if (stateHandler.quarterCount == 0)
                stateHandler.setNoQuarterState()
            else {
                stateHandler.setHasQuarterState()
            }
        }
    }

    override fun toString(): String {
        return "delivering a gumball"
    }

}