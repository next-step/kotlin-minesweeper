package minesweeper

class CharBoard(
    private val boardDimensions: BoardDimensions
): GameBoard {

    override fun render(mines: Mines): Array<Array<String>> {
        val board = Array(boardDimensions.height.value) { Array(boardDimensions.width.value) { INIT_CELL } }
        mines.mines.forEach {
            board[it.y][it.x] = GameBoard.MINE
        }
        return board
    }

    companion object {
        private const val INIT_CELL = "C"
    }
}
