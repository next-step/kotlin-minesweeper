package minesweeper.domain.block

import minesweeper.exception.InvalidAdjacentMineCountRangeException

@JvmInline
value class AdjacentMineCount private constructor(val adjacentMineCount: Int) {

    companion object {
        private const val MINIMUM_COUNT = 0
        private const val MAXIMUM_COUNT = 8

        private val CACHE = (MINIMUM_COUNT..MAXIMUM_COUNT).associateWith { AdjacentMineCount(it) }

        val DEFAULT_ADJACENT_MINE_COUNT = CACHE.getValue(MINIMUM_COUNT)

        fun from(adjacentMineCount: Int): AdjacentMineCount {
            validateRange(adjacentMineCount)
            return CACHE.getValue(adjacentMineCount)
        }

        private fun validateRange(adjacentMineCount: Int) {
            if (adjacentMineCount !in MINIMUM_COUNT..MAXIMUM_COUNT) {
                throw InvalidAdjacentMineCountRangeException(adjacentMineCount)
            }
        }
    }
}
