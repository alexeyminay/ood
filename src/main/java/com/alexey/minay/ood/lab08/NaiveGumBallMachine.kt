package com.alexey.minay.ood.lab08

class NaiveGumBallMachine(private var count: Int) : IGumBallMachine {

    private var mState: State = when {
        count > 0 -> State.NO_QUARTER
        else -> State.SOLD_OUT
    }
    private var mQuarterCount = 0

    enum class State {
        SOLD_OUT,
        NO_QUARTER,
        HAS_QUARTER,
        FULL_COIN_ACCEPTOR,
        SOLD
    }

    override fun insertQuarter() {
        when (mState) {
            State.SOLD_OUT -> println("You can't insert a quarter, the machine is sold out\n")
            State.NO_QUARTER -> {
                mQuarterCount++
                println("You inserted a quarter\n")
                mState = State.HAS_QUARTER
            }
            State.HAS_QUARTER -> {
                mQuarterCount++
                println("You inserted a quarter\n")
                if (mQuarterCount == 5) {
                    mState = State.FULL_COIN_ACCEPTOR
                }
            }
            State.FULL_COIN_ACCEPTOR -> println("You can't insert another quarter\n")
            State.SOLD -> println("Please wait, we're already giving you a gumball\n")
        }
    }

    override fun ejectQuarters() {
        when (mState) {
            State.SOLD_OUT -> println("You can't eject, you haven't inserted a quarter yet\n")
            State.NO_QUARTER -> println("You haven't inserted a quarter\n")
            State.FULL_COIN_ACCEPTOR,
            State.HAS_QUARTER -> {
                println("Quarters returned \n")
                mQuarterCount = 0
                mState = State.NO_QUARTER
            }
            State.SOLD -> println("Sorry you already turned the crank\n")
        }
    }

    override fun turnCrank() {
        when (mState) {
            State.SOLD_OUT -> {
                println("You turned but there's no gumballs\n")
            }
            State.NO_QUARTER -> println("You turned but there's no quarter\n")
            State.FULL_COIN_ACCEPTOR,
            State.HAS_QUARTER -> {
                println("You turned...\n")
                mState = State.SOLD
                dispense()
            }
            State.SOLD -> println("Turning twice doesn't get you another gumball\n")
        }
    }

    override fun refill(numBalls: Int) {
        if(mState == State.SOLD) {
            println("Wait please until gumball rolling out...\n")
            return
        }
        count = numBalls
        mState = when {
            count > 0 -> State.NO_QUARTER
            else -> State.SOLD_OUT
        }
    }

    override fun toString(): String {
        return "NaiveGumBallMachine state: ${mState.name} \nGumBallCount = $count QuarterCount = $mQuarterCount \n"
    }

    private fun dispense() {
        when (mState) {
            State.HAS_QUARTER,
            State.FULL_COIN_ACCEPTOR,
            State.SOLD_OUT -> println("No gumball dispensed\n")
            State.NO_QUARTER -> println("You need to pay first\n")
            State.SOLD -> {
                mState = if (count == 0) {
                    println("Oops, out of gumballs\n")
                    println("Quarters returned \n")
                    mQuarterCount = 0
                    State.SOLD_OUT
                } else {
                    --count
                    --mQuarterCount
                    println("A gumball comes rolling out the slot...\n")
                    if (mQuarterCount == 0) {
                        State.NO_QUARTER
                    } else {
                        State.HAS_QUARTER
                    }
                }
            }
        }
    }

}