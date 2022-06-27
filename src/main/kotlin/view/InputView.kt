package view

import constants.ErrorMessages
import constants.Messages

/**
 * 입력을 받는 클래스
 * Created by Jaesungchi on 2022.06.28..
 */
object InputView {

    fun getHeight(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(Messages.WRITE_HEIGHT)
        val input = readStringValue()
        require(!input.isNullOrBlank()) { ErrorMessages.IS_NULL_OR_EMPTY }
        require(input.toIntOrNull() != null) { ErrorMessages.IS_NOT_NUMBER }
        require(input.toInt() > 0) { ErrorMessages.IS_UNDER_ZERO }
        return input.toInt()
    }
}
