package minesweeper.domain.cell.coordinate

@JvmInline
value class Column(
    val value: Int,
) {
    init {
        require(value >= MINIMUM_COLUMN) { "열은 ${MINIMUM_COLUMN}이상 이어야 합니다." }
    }

    companion object {
        private const val MINIMUM_COLUMN = 0
    }
}
