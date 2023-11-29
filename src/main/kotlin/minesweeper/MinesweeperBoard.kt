package minesweeper

class MinesweeperBoard(
    private val gameBoard: GameBoard,
    private val mines: Mines
) {
    fun calculateAdjacentMineCounts() = gameBoard.toIntBoard()
        .let { mines.nearIncrementCellNumber(it) }
}
