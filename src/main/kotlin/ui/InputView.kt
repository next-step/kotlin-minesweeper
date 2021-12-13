package ui

import domain.ExceptionTypes.INPUT_HEIGHT_MUST_NOT_NULL_OR_EMPTY
import domain.ExceptionTypes.INPUT_MUST_INT
import domain.ExceptionTypes.INPUT_NUMBER_OF_MINES_MUST_NOT_NULL_OR_EMPTY
import domain.ExceptionTypes.INPUT_NUMBER_OF_MINES_MUST_NOT_OVER_LIMIT
import domain.ExceptionTypes.INPUT_WIDTH_MUST_NOT_NULL_OR_EMPTY

object InputView {
    private val unsignedNumberRegex = "\\d*".toRegex()

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

    private fun isUnsignedInt(numberOfString: String) = numberOfString.matches(unsignedNumberRegex)
}
