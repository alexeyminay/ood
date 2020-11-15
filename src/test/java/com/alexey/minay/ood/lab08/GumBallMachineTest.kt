package com.alexey.minay.ood.lab08

import com.alexey.minay.ood.lab08.states.*
import org.junit.Before
import org.junit.Test

class GumBallMachineTest {

    private lateinit var mGumBallMachine: GumBallMachine

    @Before
    fun setup() {
        mGumBallMachine = GumBallMachine(0)
    }

    @Test
    fun shouldBeSoldOutState() {
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldNotChangeStateIfEjectQuarter() {
        mGumBallMachine.ejectQuarters()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldNotChangeStateIfInsertQuarter() {
        mGumBallMachine.insertQuarter()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldNotChangeStateIfTurnCrank() {
        mGumBallMachine.turnCrank()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldBeNoQuarterState() {
        mGumBallMachine.refill(5)
        assert(mGumBallMachine.state is NoQuarterState)
    }

    @Test
    fun shouldSetChangeToSoldOutState() {
        mGumBallMachine.setSoldOutState()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldSetNoQuarterState() {
        mGumBallMachine.setNoQuarterState()
        assert(mGumBallMachine.state is NoQuarterState)
    }

    @Test
    fun shouldSetSoldState() {
        mGumBallMachine.setSoldState()
        assert(mGumBallMachine.state is SoldState)
    }

    @Test
    fun shouldSetHasQuarterState() {
        mGumBallMachine.setHasQuarterState()
        assert(mGumBallMachine.state is HasQuarterState)
    }

    @Test
    fun shouldSetFullCoinAcceptorState() {
        mGumBallMachine.setFullCoinAcceptorState()
        assert(mGumBallMachine.state is FullCoinAcceptorState)
    }

    @Test
    fun shouldBeHasQuarterState() {
        mGumBallMachine.refill(5)
        mGumBallMachine.insertQuarter()
        assert(mGumBallMachine.state is HasQuarterState)
    }

    @Test
    fun shouldBeNoQuarterStateIfTurnCrank() {
        mGumBallMachine.refill(5)
        mGumBallMachine.insertQuarter()
        mGumBallMachine.turnCrank()
        assert(mGumBallMachine.state is NoQuarterState)
    }

    @Test
    fun shouldBeSoldOutIfTurnCrank() {
        mGumBallMachine.refill(1)
        mGumBallMachine.insertQuarter()
        mGumBallMachine.turnCrank()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldInsertThreeQuarter() {
        mGumBallMachine.refill(1)
        val insertCount = 3
        for (i in 0 until insertCount) {
            mGumBallMachine.insertQuarter()
        }
        assert(insertCount == mGumBallMachine.quarterCount)
    }

    @Test
    fun shouldInsertOnlyFiveQuarter() {
        mGumBallMachine.refill(1)
        val insertCount = 100
        val maxCount = 5
        for (i in 0 until insertCount) {
            mGumBallMachine.insertQuarter()
        }
        assert(maxCount == mGumBallMachine.quarterCount)
    }

    @Test
    fun shouldBeCoinFillAcceptorState() {
        mGumBallMachine.refill(1)
        val insertCount = 100
        for (i in 0 until insertCount) {
            mGumBallMachine.insertQuarter()
        }
        assert(mGumBallMachine.state is FullCoinAcceptorState)
    }

    @Test
    fun shouldReleaseBall() {
        mGumBallMachine.refill(1)
        mGumBallMachine.insertQuarter()
        mGumBallMachine.turnCrank()
        assert(mGumBallMachine.state is SoldOutState)
    }

    @Test
    fun shouldFillWhenCreated() {
        mGumBallMachine = GumBallMachine(1)
        assert(mGumBallMachine.state is NoQuarterState)
    }

    @Test
    fun shouldRefillWhenQuarterInserted() {
        mGumBallMachine.refill(1)
        mGumBallMachine.insertQuarter()
        val expectedGumBall = 10
        mGumBallMachine.refill(expectedGumBall)
        assert(mGumBallMachine.count == expectedGumBall)
    }

    @Test
    fun shouldRefillWhenNoQuarterState() {
        mGumBallMachine.refill(1)
        val expectedGumBall = 10
        mGumBallMachine.refill(expectedGumBall)
        assert(mGumBallMachine.count == expectedGumBall)
    }

    @Test
    fun shouldReturnCoinsIfGumballsSoldOut() {
        mGumBallMachine.refill(1)
        mGumBallMachine.insertQuarter()
        mGumBallMachine.insertQuarter()
        mGumBallMachine.turnCrank()
        assert(mGumBallMachine.quarterCount == 0)
    }

}