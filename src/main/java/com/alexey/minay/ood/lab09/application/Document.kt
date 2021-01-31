package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler

class Document(
    val imageStateHandler: ImageStateHandler,
    history: CommandHistory,
)