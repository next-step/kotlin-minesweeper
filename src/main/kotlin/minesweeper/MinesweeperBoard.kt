package minesweeper

class MinesweeperBoard(
    private val gameBoard: GameBoard,
    private val mines: Mines
) {
    fun render() = gameBoard.render(mines)
}
