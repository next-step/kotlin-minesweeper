package domain

import dto.CellDto

class Fixture {
    val board = Board(height, width)
    val selector = object : PositionSelector(height, width) {
        override fun selectMinePositions(number: Int, excludedPosition: Position): List<Position> = minePositions
    }

    fun boardMineNumbers(): String = board.joinToString(ROW_SEPARATOR) { row ->
        row.joinToString(CELL_SEPARATOR) { toMineNumber(it) }
    }

    fun renderBoard(): String = board.joinToString(ROW_SEPARATOR) { row ->
        row.joinToString(CELL_SEPARATOR) { render(it) }
    }

    fun drawBoard(): String = board.joinToString(ROW_SEPARATOR) { row ->
        row.joinToString(CELL_SEPARATOR) { draw(it) }
    }

    private fun toMineNumber(cell: Cell): String = CellDto(cell).mineNumber()
    private fun render(cell: Cell): String = CellDto(cell).render()
    private fun draw(cell: Cell): String = if (cell.isMine()) "*" else "C"

    companion object {
        const val height = 10
        const val width = 10
        const val mineNumber = 10
        val position = Position(1, 1)
        val minePositions = listOf(
            Position(1, 4),
            Position(1, 8),
            Position(2, 3),
            Position(2, 5),
            Position(5, 1),
            Position(6, 7),
            Position(7, 3),
            Position(7, 7),
            Position(8, 7),
            Position(8, 10)
        )
        private const val ROW_SEPARATOR = "\n"
        private const val CELL_SEPARATOR = " "
    }
}
