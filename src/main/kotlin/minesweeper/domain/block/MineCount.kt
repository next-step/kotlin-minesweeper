package minesweeper.domain.block

@JvmInline
value class MineCount private constructor(private val mineCount: Int) {

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
        private const val MAXIMUM_MINE_COUNT = 8
        private val CACHE = (MINIMUM_MINE_COUNT..MAXIMUM_MINE_COUNT).associateWith { MineCount(it) }

        fun from(mineCount: Int): MineCount = CACHE.getValue(mineCount)
    }
}
