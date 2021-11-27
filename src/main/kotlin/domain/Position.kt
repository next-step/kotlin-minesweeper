package domain

import exception.IllegalPositionException

data class Position(
    val rowIndex: Int = MIN,
    val columnIndex: Int = MIN
) {
    constructor(pair: Pair<Int, Int>) : this(
        rowIndex = pair.first,
        columnIndex = pair.second
    )

    init {
        if (rowIndex < MIN || columnIndex < MIN) {
            throw IllegalPositionException()
        }
    }

    companion object {
        private const val MIN = 1
    }
}
