package com.alexey.minay.ood.lab08

import com.alexey.minay.ood.lab08.states.*

class GumBallMachineWithDynamicallyCreatedState(
        private var count: Int
) : IGumBallMachine, IStateHandler {

    override var quarterCount = 0

    private var mState: IState = when {
        count > 0 -> NoQuarterState(this)
        else -> SoldState(this)
    }

    override fun toString(): String {
        return "GumBallMachineWithDynamicallyCreatedState state: $mState \nGumBallCount = $count QuarterCount = $quarterCount \n"
    }

    override fun ejectQuarters() {
        mState.ejectQuarter()
    }

    override fun insertQuarter() {
        mState.insertQuarter()
    }

    override fun turnCrank() {
        mState.turnCrank()
        mState.dispense()
    }

    override fun refill(numBalls: Int) {
        if(mState is SoldState) {
            println("Wait please until gumball rolling out...\n")
            return
        }
        count = numBalls
        mState = when {
            count > 0 -> NoQuarterState(this)
            else -> SoldState(this)
        }
    }

    override fun releaseBall() {
        if (count != 0) {
            println("A gumball comes rolling out the slot...\n")
            --count
        }
    }

    override fun setSoldOutState() {
        mState = SoldOutState()
        quarterCount = 0
    }

    override fun setNoQuarterState() {
        mState = NoQuarterState(this)
        quarterCount = 0
    }

    override fun setSoldState() {
        mState = SoldState(this)
    }

    override fun setHasQuarterState() {
        mState = HasQuarterState(this)
    }

    override fun setFullCoinAcceptorState() {
        mState = FullCoinAcceptorState(this)
    }

    override fun getBallCount() = count

}
