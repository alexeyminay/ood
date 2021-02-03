package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.common.AppPoint

interface ICanvas {
    fun drawLine(from: AppPoint, to: AppPoint)
    fun drawEllipse(rightTop: AppPoint, width: Double, height: Double)
    fun fill(mListX: List<Double>, mListY: List<Double>)
    fun fillEllipse(rightTop: AppPoint, width: Double, height: Double)
    fun clear()
}