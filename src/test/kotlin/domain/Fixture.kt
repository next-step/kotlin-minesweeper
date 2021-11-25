package domain

class Fixture {
    private val board = Board(HEIGHT, WIDTH)
    operator fun component1(): Board = board
    operator fun component3(): MineNumber = MineNumber(minePositions.size)
    operator fun component2(): PositionSelector = object : PositionSelector(HEIGHT, WIDTH) {
        override fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position) = minePositions
    }

    fun drawnBoard(): String = toString { cell -> drawnCell(cell) }
    fun mineNumbers(): String = toString { cell -> mineNumber(cell) }
    fun renderedBoard(): String = toString { cell -> renderedCell(cell) }
    private fun toString(toString: (Cell) -> String): String = board.joinToString(ROW_SEPARATOR) {
        it.joinToString(CELL_SEPARATOR) { cell -> toString(cell) }
    }

    private fun drawnCell(cell: Cell): String = if (cell.isMine()) MINE else CLOSED
    private fun mineNumber(cell: Cell): String = if (cell.isMine()) MINE else cell.mineNumber().toString()
    private fun renderedCell(cell: Cell): String = if (!cell.isOpen() || cell.isMine()) CLOSED else mineNumber(cell)

    companion object {
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
        private const val HEIGHT = 10
        private const val WIDTH = 10
        private const val MINE = "*"
        private const val CLOSED = "C"
        private const val CELL_SEPARATOR = " "
        private const val ROW_SEPARATOR = "\n"
    }
}
