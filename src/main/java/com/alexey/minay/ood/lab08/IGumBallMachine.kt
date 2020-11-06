package com.alexey.minay.ood.lab08

interface IGumBallMachine {
    fun insertQuarter()
    fun ejectQuarters()
    fun turnCrank()
    fun refill(numBalls: Int)
}