package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.domain.IFileRepository
import com.alexey.minay.ood.lab09.ui.MVP
import java.io.File

class FileTabPresenter(
        private val fileRepository: IFileRepository
) : MVP.IFileTabPresenter {

    private var mView: MVP.IFileTabView? = null

    override fun onViewCreated(view: MVP.IFileTabView) {
        mView = view
    }

    override fun onSave(file: File) {
        fileRepository.saveFile(file)
    }

    override fun onSaveAs(file: File) {
        fileRepository.saveFile(file)
    }

    override fun onOpen(file: File) {
        fileRepository.openFile(file)
    }
}