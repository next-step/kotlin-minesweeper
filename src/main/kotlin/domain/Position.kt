package domain

import exception.IllegalPositionException

@JvmInline
value class Position(val pair: Pair<Int, Int>) {
    constructor(i: Int, j: Int) : this(Pair(i, j))

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
