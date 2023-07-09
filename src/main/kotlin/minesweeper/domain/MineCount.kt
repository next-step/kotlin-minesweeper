package minesweeper.domain

class MineCount(val value: Int) {
    init {
        require(value >= MIN_MINE_COUNT_VALUE) { "지뢰 개수는 $MIN_MINE_COUNT_VALUE 이상만 허용됩니다." }
    }

    companion object {
        private const val MIN_MINE_COUNT_VALUE = 0
    }
}