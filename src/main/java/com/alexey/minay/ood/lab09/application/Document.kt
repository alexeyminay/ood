package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.stateHandler.State

class Document(
    val imageStateHandler: State,
    history: CommandHistory,
)