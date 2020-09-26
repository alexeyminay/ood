package com.alexey.minay.ood.lab08

import com.alexey.minay.ood.lab08.states.*

class GumBallMachine(private var count: Int) : IGumBallMachine, IStateHandler {

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

    private var mState: IState = when {
        count > 0 -> mNoQuarterState
        else -> mSoldOutState
    }

    override fun toString(): String {
        return "GumBallMachine state: $mState \nGumBallCount = $count QuarterCount = $quarterCount \n"
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
        if (mState is SoldState) {
            println("Wait please until gumball rolling out...\n")
            return
        }
        println("GumBallMachine has $numBalls gum balls...\n")
        count = numBalls
        mState = when {
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
        mState = mSoldOutState
        quarterCount = 0
    }

    override fun setNoQuarterState() {
        mState = mNoQuarterState
        quarterCount = 0
    }

    override fun setSoldState() {
        mState = mSoldState
    }

    override fun setHasQuarterState() {
        mState = mHasQuarterState
    }

    override fun setFullCoinAcceptorState() {
        mState = mFullCoinAcceptorState
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

