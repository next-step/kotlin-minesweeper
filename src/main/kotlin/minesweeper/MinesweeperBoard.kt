package minesweeper

class MinesweeperBoard(
    private val gameBoard: GameBoard,
    private val mines: Mines
) {
    fun toBooleanBoard() = gameBoard.toBooleanBoard().apply {
        mines.mines.forEach { this[it.x][it.y] = true }
    }
}
