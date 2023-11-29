package minesweeper

@JvmInline
value class Mines(
    private val mines: Set<Position>
) {

    fun nearIncrementCellNumber(board: Array<Array<Int>>): Array<Array<Int>> {
        val copiedBoard = board.copyOf()
        mines.forEach { mine ->
            copiedBoard[mine.y][mine.x] = MINE_NUMBER_CELL
            copiedBoard.incrementNearCell(mine)
        }
        return copiedBoard
    }

    private fun Array<Array<Int>>.incrementNearCell(mine: Position) {
        NEAR_CELLS.map { it + mine }
            .filter { this.isNotOufOfRange(it) }
            .forEach {
                if (this[it.y][it.x] != MINE_NUMBER_CELL) this[it.y][it.x]++
            }
    }

    private fun Array<Array<Int>>.isNotOufOfRange(position: Position): Boolean =
        this.size > position.x && this.first().size > position.y && position.x >= 0 && position.y >= 0

    companion object {
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
