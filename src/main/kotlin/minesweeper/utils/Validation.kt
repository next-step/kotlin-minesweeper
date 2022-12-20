package minesweeper.utils

object Validation {

    fun isNumeric(value: String): Boolean {
        return value.all { Character.isDigit(it) }
    }
}
