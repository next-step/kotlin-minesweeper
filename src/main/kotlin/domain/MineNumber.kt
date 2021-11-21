package domain

import exception.IllegalMineNumberException

@JvmInline
value class MineNumber(val number: Int = MIN) {
    init {
        if (number < MIN) {
            throw IllegalMineNumberException()
        }
    }

    fun isBlank(): Boolean = number == MIN
    operator fun inc() = MineNumber(number + ONE)

    companion object {
        private const val MIN = 0
        private const val ONE = 1
    }
}
