package domain

class MineField(
    private val height: Height,
    private val width: Width,
    private val mineCount: Int,
) {
    private val grid: Cells

    init {
        require(mineCount <= height.value * width.value) { MINE_MAXIMUM_EXCEPTION_MESSAGE }
        val minePositions = generateAllPositions().shuffled().take(mineCount).toSet()
        grid = Cells.create(height.value, width.value, minePositions).addNumberHints()
    }

    private fun generateAllPositions(): List<Position> =
        List(height.value * width.value) { index ->
            Position(index / width.value, index % width.value)
        }

    fun getState(): MineFieldState = MineFieldState(grid)

    fun openCell(
        row: Int,
        column: Int,
    ): Boolean {
        if (grid.isCellMine(row, column)) {
            grid.openCell(row, column) // 열린 지뢰 표시
            return false
        }
        grid.openCell(row, column)
        return true
    }

    companion object {
        private const val MINE_MAXIMUM_EXCEPTION_MESSAGE = "지뢰는 총 셀 수를 초과할 수 없습니다."
    }
}
