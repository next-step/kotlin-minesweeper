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
        grid = createMineField()
    }

    private fun createMineField(): Grid {
        val positions = generateAllPositions().shuffled().take(mineCount).toSet()
        val cells = List(height.value) { row ->
            List(width.value) { col ->
                val isMine = positions.contains(Position(row, col))
                Cell.create(isMine)
            }
        }
        return Grid(height, width, cells)
    }

    private fun generateAllPositions(): List<Position> =
        (0 until height.value).flatMap { row ->
            (0 until width.value).map { col ->
                Position(row, col)
            }
        }

    fun display(): List<String> = grid.toDisplayRows(cellMapper)

    companion object {
        private const val MINE_MAXIMUM_EXCEPTION_MESSAGE = "지뢰는 총 셀 수를 초과할 수 없습니다."
    }
}
