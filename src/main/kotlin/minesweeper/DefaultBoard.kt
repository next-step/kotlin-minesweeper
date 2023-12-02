package minesweeper

class DefaultBoard(
    private val boardDimensions: BoardDimensions
): GameBoard {

    override fun render(mines: Mines): RenderedGameBoard {
        val board = Array(boardDimensions.height.value) { Array(boardDimensions.width.value) { INIT_CELL } }
        return RenderedGameBoard(board)
    }

    companion object {
        private const val INIT_CELL = 'C'
    }
}
