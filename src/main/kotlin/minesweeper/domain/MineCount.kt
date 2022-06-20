package minesweeper.domain

@JvmInline
value class MineCount(private val count: Int) {
    init {
        require(count > 1) { "지뢰는 0개 보다 많아야합니다." }
    }
}
