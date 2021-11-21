package domain

import exception.IllegalMineNumberException

@JvmInline
value class MineNumber(val number: Int = DEFAULT_NUMBER) {
    init {
        if (number < DEFAULT_NUMBER) {
            throw IllegalMineNumberException()
        }
    }

    operator fun inc() = MineNumber(number + ONE)

    companion object {
        private const val DEFAULT_NUMBER = 0
        private const val ONE = 1
    }
}
