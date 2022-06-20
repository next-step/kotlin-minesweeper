package minesweeper.model.board

class SurroundMineCount(private val value: Int) : Comparable<Int> {
    init {
        require(value in RANGE_OF_SURROUND_MINE_COUNT)
    }

    override fun compareTo(other: Int): Int = this.value.compareTo(other)
    override fun equals(other: Any?): Boolean {
        if (other is Int) {
            return this.value == other
        }
        return super.equals(other)
    }

    companion object {
        private const val MIN_SURROUND_MINE_COUNT = 0
        private const val MAX_SURROUND_MINE_COUNT = 8
        private val RANGE_OF_SURROUND_MINE_COUNT = MIN_SURROUND_MINE_COUNT..MAX_SURROUND_MINE_COUNT
    }
}
