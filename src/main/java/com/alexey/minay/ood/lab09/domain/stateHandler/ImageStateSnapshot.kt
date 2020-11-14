package com.alexey.minay.ood.lab09.domain.stateHandler

import com.alexey.minay.ood.lab09.domain.shapes.IShape

data class ImageStateSnapshot(
        val shapes: List<IShape>
)