package com.alexey.minay.ood.lab04

import java.io.InputStream

interface IDesigner {
    fun createDraft(stream: InputStream): PictureDraft
}