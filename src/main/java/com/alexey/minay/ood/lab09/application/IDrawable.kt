package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.ICanvas

interface IDrawable {
    fun draw(canvasAdapter: ICanvas)
}