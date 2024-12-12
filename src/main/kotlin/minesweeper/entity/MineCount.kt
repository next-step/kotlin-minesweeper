package minesweeper.entity

@JvmInline
value class MineCount(
    val value: Int,
) {
    init {
        require(value > 0) { "지뢰 개수는 0보다 작을 수 없습니다." }
    }

    fun validate(totalCells: Int) {
        require(value < totalCells) {
            "지뢰 개수는 전체 셀 수를 초과할 수 없습니다. (total: $totalCells, mineCount: $value)"
        }
    }
}
