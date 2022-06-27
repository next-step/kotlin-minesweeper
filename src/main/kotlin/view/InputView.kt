package view

import constants.Messages

/**
 * 입력을 받는 클래스
 * Created by Jaesungchi on 2022.06.28..
 */
object InputView {

    fun getHeight(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(Messages.WRITE_HEIGHT)
        val input = readStringValue()
        return input!!.toInt()
    }
}
