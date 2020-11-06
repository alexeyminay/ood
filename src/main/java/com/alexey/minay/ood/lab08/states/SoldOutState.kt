package com.alexey.minay.ood.lab08.states


class SoldOutState : IState {

    override fun insertQuarter() {
        println("You can't insert a quarter, the machine is sold out\n")
    }

    override fun ejectQuarter() {
        println("You can't eject, you haven't inserted a quarter yet\n")
    }

    override fun turnCrank() {
        println("You turned but there's no gumballs\n")
    }

    override fun dispense() {
        println("No gumball dispensed\n")
    }

    override fun toString(): String {
        return "sold out"
    }

}