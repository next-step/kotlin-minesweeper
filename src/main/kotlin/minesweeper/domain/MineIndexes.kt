package minesweeper.domain

class MineIndexes(private val indexes: List<Int>) {
    init {
        require(indexes.size >= MIN_MINE_COUNT_VALUE) { "지뢰 개수는 $MIN_MINE_COUNT_VALUE 이상만 허용됩니다." }
    }

    fun contains(index: Int): Boolean {
        return indexes.contains(index)
    }

    fun size(): Int {
        return indexes.size
    }

    companion object {
        private const val MIN_MINE_COUNT_VALUE = 0
    }
}
