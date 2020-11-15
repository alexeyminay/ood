package com.alexey.minay.ood.lab08

import com.alexey.minay.ood.lab08.states.*

class GumBallMachine(count: Int) : IGumBallMachine, IStateHandler {

    private val mSoldState: IState
    private val mSoldOutState: IState
    private val mNoQuarterState: IState
    private val mHasQuarterState: IState
    private val mFullCoinAcceptorState: IState

    override var quarterCount = 0

    init {
        mSoldState = SoldState(this)
        mSoldOutState = SoldOutState()
        mNoQuarterState = NoQuarterState(this)
        mHasQuarterState = HasQuarterState(this)
        mFullCoinAcceptorState = FullCoinAcceptorState(this)
    }

    var state: IState = when {
        count > 0 -> mNoQuarterState
        else -> mSoldOutState
    }
        private set

    var count = count
        private set


    override fun toString(): String {
        return "GumBallMachine state: $state \nGumBallCount = $count QuarterCount = $quarterCount \n"
    }

    override fun ejectQuarters() {
        state.ejectQuarter()
    }

    override fun insertQuarter() {
        state.insertQuarter()
    }

    override fun turnCrank() {
        state.turnCrank()
        state.dispense()
    }

    override fun refill(numBalls: Int) {
        if (state is SoldState) {
            println("Wait please until gumball rolling out...\n")
            return
        }
        println("GumBallMachine has $numBalls gum balls...\n")
        count = numBalls
        state = when {
            count > 0 -> mNoQuarterState
            else -> mSoldOutState
        }
    }

    override fun releaseBall() {
        if (count != 0) {
            println("A gumball comes rolling out the slot...\n")
            --count
        }
    }

    override fun setSoldOutState() {
        state = mSoldOutState
        quarterCount = 0
    }

    override fun setNoQuarterState() {
        state = mNoQuarterState
        quarterCount = 0
    }

    override fun setSoldState() {
        state = mSoldState
    }

    override fun setHasQuarterState() {
        state = mHasQuarterState
    }

    override fun setFullCoinAcceptorState() {
        state = mFullCoinAcceptorState
    }

    override fun getBallCount() = count

}

interface IStateHandler {

    var quarterCount: Int
    fun releaseBall()
    fun setSoldOutState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setHasQuarterState()
    fun setFullCoinAcceptorState()
    fun getBallCount(): Int

}

