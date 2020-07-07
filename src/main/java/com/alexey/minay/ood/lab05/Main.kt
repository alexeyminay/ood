package com.alexey.minay.ood.lab05

import com.alexey.minay.ood.lab05.commands.InsertParagraphCommand
import com.alexey.minay.ood.lab05.commands.SaveCommand
import com.alexey.minay.ood.lab05.commands.SetTitleCommand
import com.alexey.minay.ood.lab05.commands.UndoCommand
import com.alexey.minay.ood.lab05.document.HTMLDocument

fun main(args: Array<String>){
    val document = HTMLDocument()
    val receiver = Receiver()
    val insertParagraph1 = InsertParagraphCommand(document, "paragraph 1",0)
    val insertParagraph2 = InsertParagraphCommand(document, "paragraph 2",0)
    val undo = UndoCommand(document)
    val setTitle = SetTitleCommand(document, "hey")
    val save = SaveCommand(document, "")
    receiver.add(insertParagraph1)
    receiver.add(insertParagraph2)
    receiver.add(undo)
    receiver.add(setTitle)
    receiver.add(save)
    receiver.run()
}

