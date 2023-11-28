package minesweeper

class MinesweeperBoard(
    val gameBoard: GameBoard,
    val mines: Mines
) {
    fun toBooleanBoard() = gameBoard.toBooleanBoard().apply {
        mines.mines.forEach { this[it.x][it.y] = true }
    }
}
