package domain

class MineField(
    private val height: Height,
    private val width: Width,
    private val mineCount: Int,
    private val cellMapper: CellMapper = DefaultCellMapper(),
) {
    private val grid: Grid

    init {
        require(mineCount <= height.value * width.value) { MINE_MAXIMUM_EXCEPTION_MESSAGE }
        val minePositions = generateAllPositions().shuffled().take(mineCount).toSet()
        val cells = Cells.create(height.value, width.value, minePositions)
        grid = Grid(height, width, cells).withNumberHints()
    }

    private fun generateAllPositions(): List<Position> =
        List(height.value * width.value) { index ->
            Position(index / width.value, index % width.value)
        }

    fun getState(): MineFieldState = MineFieldState(grid.getCells())

    fun openCell(
        row: Int,
        col: Int,
    ): Boolean {
        val cell = grid.getCells()[row][col]
        return if (cell.isMine()) {
            false
        } else {
            val positionsToOpen = cell.open(row, col, grid.getCells())
            positionsToOpen.forEach { (r, c) ->
                grid.getCells()[r][c].isOpen = true
            }
            true
        }
    }

    companion object {
        private const val MINE_MAXIMUM_EXCEPTION_MESSAGE = "지뢰는 총 셀 수를 초과할 수 없습니다."
    }
}
