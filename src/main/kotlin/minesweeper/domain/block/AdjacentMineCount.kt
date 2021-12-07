package minesweeper.domain.block

@JvmInline
value class AdjacentMineCount private constructor(private val adjacentMineCount: Int) {

    companion object {
        private const val MINIMUM_COUNT = 0
        private const val MAXIMUM_COUNT = 8
        private val CACHE = (MINIMUM_COUNT..MAXIMUM_COUNT).associateWith { AdjacentMineCount(it) }

        fun from(mineCount: Int): AdjacentMineCount = CACHE.getValue(mineCount)
    }
}
