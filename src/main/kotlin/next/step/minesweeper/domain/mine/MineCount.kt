package next.step.minesweeper.domain.mine

@JvmInline
value class MineCount(val count: Int) {
    operator fun inc(): MineCount = MineCount(count + 1)

    init {
        require(count > MIN_COUNT) { "지뢰 개수는 ${MIN_COUNT}보다 커야합니다." }
    }

    companion object {
        private const val MIN_COUNT = 0
    }
}
