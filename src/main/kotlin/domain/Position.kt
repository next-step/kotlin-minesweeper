package domain

import exception.IllegalPositionException

@JvmInline
value class Position(val pair: Pair<Int, Int>) {
    constructor(i: Int = MIN, j: Int = MIN) : this(i to j)

    init {
        val (i, j) = pair
        if (i < MIN || j < MIN) {
            throw IllegalPositionException()
        }
    }

    companion object {
        private const val MIN = 1
    }
}
