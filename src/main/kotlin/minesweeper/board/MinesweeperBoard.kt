package minesweeper.board

data class MinesweeperGameBoard(
    override val board: Board
): GameBoard()
