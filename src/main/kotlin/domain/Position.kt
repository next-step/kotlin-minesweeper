package domain

import exception.IllegalPositionException

@JvmInline
value class Position(val pair: Pair<Int, Int>) {
    constructor(rowIndex: Int = MIN, columnIndex: Int = MIN) : this(rowIndex to columnIndex)

    init {
        val (rowIndex, columnIndex) = pair
        if (rowIndex < MIN || columnIndex < MIN) {
            throw IllegalPositionException()
        }
    }

    companion object {
        private const val MIN = 1
    }
}
