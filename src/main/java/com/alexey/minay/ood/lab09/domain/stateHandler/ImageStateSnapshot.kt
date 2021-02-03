package com.alexey.minay.ood.lab09.domain.stateHandler

import com.alexey.minay.ood.lab09.application.IAppShape

data class ImageStateSnapshot(
        val shapes: List<IAppShape>
)