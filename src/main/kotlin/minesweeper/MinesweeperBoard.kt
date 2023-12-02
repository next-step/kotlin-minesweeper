package minesweeper

class MinesweeperBoard(
    private val playerBoard: RenderedGameBoard,
    private val adminBoard: RenderedGameBoard,
    private val mines: Mines
) {
    constructor(playerBoard: GameBoard, adminBoard: GameBoard, mines: Mines):
            this(playerBoard.render(mines), adminBoard.render(mines), mines)

    fun openCell(it: Position) {
        TODO("셀 오픈")
    }

    fun playerBoardRender() = playerBoard.joinToString()
}
