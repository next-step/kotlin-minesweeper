package minesweeper.board

class GameBoardRenderer(
    private val boardElement: BoardElement,
    private val renderStrategy: GameBoardRenderStrategy
) {

    fun render(cell: Char = DEFAULT_CELL): GameBoard =
        GameBoard(renderStrategy(boardElement, cell))

    companion object {
        private const val DEFAULT_CELL = 'C'
    }
}
