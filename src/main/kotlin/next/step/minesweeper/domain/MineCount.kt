package next.step.minesweeper.domain

data class MineCount(val count: Int) {
    init {
        require(count > MIN_COUNT) { "지뢰 개수는 ${MIN_COUNT}보다 커야합니다." }
    }

    companion object {
        private const val MIN_COUNT = 0
        fun of(count: Int): MineCount = MineCount(count)
    }
}