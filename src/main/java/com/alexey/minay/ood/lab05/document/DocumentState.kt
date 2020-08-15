package com.alexey.minay.ood.lab05.document

data class DocumentState(
        val document: List<IDocumentItem> = mutableListOf(),
        val title: String = ""
)