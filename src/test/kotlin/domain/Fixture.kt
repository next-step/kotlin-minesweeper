package domain

class Fixture {
    private val board = Board(HEIGHT, WIDTH)

    operator fun component1(): Board = board

    operator fun component2(): PositionSelector = object : PositionSelector(HEIGHT, WIDTH) {
        override fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position) = minePositions
    }

    operator fun component3(): MineNumber = MineNumber(minePositions.size)

    fun drawnBoard(): String = boardToString { cell -> drawnCell(cell) }

    fun mineNumbers(): String = boardToString { cell -> mineNumber(cell) }

    fun renderedBoard(): String = boardToString { cell -> renderedCell(cell) }

    private fun boardToString(cellToString: (Cell) -> String): String = board
        .joinToString(ROW_SEPARATOR) {
            it.joinToString(CELL_SEPARATOR) { cell -> cellToString(cell) }
        }

    private fun drawnCell(cell: Cell): String =
        if (cell.hasMine()) MINE else CLOSED

    private fun mineNumber(cell: Cell): String =
        if (cell.hasMine()) MINE else cell.mineNumber().toString()

    private fun renderedCell(cell: Cell): String =
        if (!cell.isOpen() || cell.hasMine()) CLOSED else cell.mineNumber().toString()

    companion object {
        private const val HEIGHT = 10
        private const val WIDTH = 10
        private const val MINE = "*"
        private const val CLOSED = "C"
        private const val CELL_SEPARATOR = " "
        private const val ROW_SEPARATOR = "\n"
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
    }
}
