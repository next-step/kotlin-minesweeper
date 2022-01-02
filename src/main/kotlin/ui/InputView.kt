package ui

import domain.ExceptionTypes.INPUT_HEIGHT_MUST_NOT_NULL_OR_EMPTY
import domain.ExceptionTypes.INPUT_MUST_INT
import domain.ExceptionTypes.INPUT_MUST_NOT_NULL_OR_EMPTY
import domain.ExceptionTypes.INPUT_NUMBER_OF_MINES_MUST_NOT_NULL_OR_EMPTY
import domain.ExceptionTypes.INPUT_NUMBER_OF_MINES_MUST_NOT_OVER_LIMIT
import domain.ExceptionTypes.INPUT_POINT_SIZE_MUST_VALIDATE
import domain.ExceptionTypes.INPUT_WIDTH_MUST_NOT_NULL_OR_EMPTY
import domain.Point

object InputView {
    private val unsignedNumberRegex = "\\d*".toRegex()
    private const val DELIMITER_FOR_REQUEST_POINT = ","
    private const val VALIDATE_POINT_SIZE = 2

    fun readInputForHeight(): Int {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_HEIGHT_MUST_NOT_NULL_OR_EMPTY }
        require(isUnsignedInt(inputString)) { INPUT_MUST_INT }
        return inputString.toInt()
    }

    fun readInputForWidth(): Int {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_WIDTH_MUST_NOT_NULL_OR_EMPTY }
        require(isUnsignedInt(inputString)) { INPUT_MUST_INT }
        return inputString.toInt()
    }

    fun readInputForNumberOfMines(maxLimit: Int): Int {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_NUMBER_OF_MINES_MUST_NOT_NULL_OR_EMPTY }
        require(isUnsignedInt(inputString)) { INPUT_MUST_INT }
        val currentValue = inputString.toInt()
        require(maxLimit >= currentValue) { INPUT_NUMBER_OF_MINES_MUST_NOT_OVER_LIMIT.format(maxLimit) }
        return currentValue
    }

    fun readInputForRequestPointForCheck(): Point {
        val inputString = readLine()
        require(!inputString.isNullOrEmpty()) { INPUT_MUST_NOT_NULL_OR_EMPTY }
        val split = inputString.split(DELIMITER_FOR_REQUEST_POINT)
        require(isSizeValidate(split.size)) { INPUT_POINT_SIZE_MUST_VALIDATE }
        split.forEach { require(isUnsignedInt(it)) { INPUT_MUST_INT } }
        return Point(split[0].toInt(), split[1].toInt())
    }

    private fun isUnsignedInt(numberOfString: String) = numberOfString.matches(unsignedNumberRegex)
    private fun isSizeValidate(size: Int) = size == VALIDATE_POINT_SIZE
}
