package domain

class MineField(
    private val height: Int,
    private val width: Int,
    private val mineCount: Int,
    private val cellMapper: CellMapper = DefaultCellMapper(),
) {
    private val field: List<List<Cell>>

    init {
        require(mineCount <= height * width) { MINE_MAXIMUM_EXCEPTION_MESSAGE }
        field = createMineField()
    }

    private fun createMineField(): List<List<Cell>> {
        val positions = generateAllPositions().shuffled().take(mineCount).toSet()
        return List(height) { row ->
            List(width) { col ->
                val isMine = positions.contains(Position(row, col))
                Cell.create(isMine)
            }
        }
    }

    private fun generateAllPositions(): List<Position> =
        (0 until height).flatMap { row ->
            (0 until width).map { col ->
                Position(row, col)
            }
        }

    fun display(): List<String> =
        field.map { row ->
            row.joinToString(" ") { cellMapper.mapToDisplay(it) }
        }

    companion object {
        private const val MINE_MAXIMUM_EXCEPTION_MESSAGE = "지뢰는 총 셀 수를 초과할 수 없습니다."
    }
}
