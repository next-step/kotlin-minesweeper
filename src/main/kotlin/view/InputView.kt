package view

import constants.ErrorMessages

/**
 * 입력을 받는 클래스
 * Created by Jaesungchi on 2022.06.28..
 */
object InputView {
    private const val WRITE_HEIGHT = "높이를 입력하세요."
    private const val WRITE_WIDTH = "너비를 입력하세요."
    private const val WRITE_MINE_COUNT = "지뢰는 몇 개인가요?"

    fun getHeight(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(WRITE_HEIGHT)
        return getInt(readStringValue)
    }

    fun getWidth(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(WRITE_WIDTH)
        return getInt(readStringValue)
    }

    fun getMineCount(readStringValue: () -> String? = { readlnOrNull() }): Int {
        println(WRITE_MINE_COUNT)
        return getInt(readStringValue)
    }

    private fun getInt(readStringValue: () -> String?): Int {
        val input = readStringValue()
        require(!input.isNullOrBlank()) { ErrorMessages.IS_NULL_OR_EMPTY }
        require(input.toIntOrNull() != null) { ErrorMessages.IS_NOT_NUMBER }
        return input.toInt()
    }
}
