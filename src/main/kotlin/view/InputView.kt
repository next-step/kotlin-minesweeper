package view

import error.ErrorMessage

object InputView {

    fun inputNumber(): Int {
        try {
            return readln().toInt()
        } catch (e: NullPointerException) {
            throw IllegalArgumentException(ErrorMessage.EMPTY_INPUT_MESSAGE.message)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(ErrorMessage.EXPECT_NUMBER_MESSAGE.message)
        }
    }

    fun inputPoint(): String {
        try {
            return readln()
        } catch (e: NullPointerException) {
            throw IllegalArgumentException(ErrorMessage.EMPTY_INPUT_MESSAGE.message)
        }
    }
}
