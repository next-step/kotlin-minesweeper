package minesweeper

class NumberBoard(
    private val boardDimensions: BoardDimensions
): GameBoard {

    override fun render(mines: Mines): Array<Array<String>> {
        val board = Array(boardDimensions.height.value) { Array(boardDimensions.width.value) { INIT_CELL } }
        mines.mines.forEach { mine ->
            board[mine.y][mine.x] = MINE_NUMBER_CELL
            board.incrementNearCell(mine)
        }

        val converted = convertToString(board)
        mines.mines.forEach { mine ->
            converted[mine.y][mine.x] = GameBoard.MINE
        }
        return converted
    }

    private fun convertToString(board: Array<Array<Int>>): Array<Array<String>> =
        board.map { row -> row.map { it.toString() }.toTypedArray() }.toTypedArray()

    private fun Array<Array<Int>>.incrementNearCell(mine: Position) {
        NEAR_CELLS.map { it + mine }
            .filter { this.isNotOufOfRange(it) }
            .forEach {
                if (this[it.y][it.x] != MINE_NUMBER_CELL) this[it.y][it.x]++
            }
    }

    private fun Array<Array<Int>>.isNotOufOfRange(position: Position): Boolean =
        this.size > position.y && this.first().size > position.x && position.x >= 0 && position.y >= 0

    companion object {
        private const val INIT_CELL = 0
        private const val MINE_NUMBER_CELL = -1
        private val NEAR_CELLS = arrayOf(
            Position(0 , -1),
            Position(1 , -1),
            Position(1 , 0),
            Position(1 , 1),
            Position(0 , 1),
            Position(-1 , 1),
            Position(-1 , 0),
            Position(-1 , -1)
        )
    }
}
