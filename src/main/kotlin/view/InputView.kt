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
        return getInt(readStringValue)
    }

    fun getWidth(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(Messages.WRITE_WIDTH)
        return getInt(readStringValue)
    }

    fun getMineCount(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(Messages.WRITE_MINE_COUNT)
        return getInt(readStringValue)
    }

    private fun getInt(readStringValue: () -> String?): Int {
        val input = readStringValue()
        require(!input.isNullOrBlank()) { ErrorMessages.IS_NULL_OR_EMPTY }
        require(input.toIntOrNull() != null) { ErrorMessages.IS_NOT_NUMBER }
        return input.toInt()
    }
}
