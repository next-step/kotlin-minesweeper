package minesweeper

class MinesweeperBoard(
    renderStrategy: GameBoardRenderStrategy,
    private val boardDimensions: BoardDimensions,
    private val mines: Mines
) {
    private val adminBoard: RenderedGameBoard = mineMarking(renderStrategy(boardDimensions, NUMBER_INIT_CELL))
    private val playerBoard: RenderedGameBoard = renderStrategy(boardDimensions, INIT_CELL)
    private val visitedBoard: RenderedGameBoard = renderStrategy(boardDimensions, INIT_CELL)

    fun openCell(pos: Position): CellOpenStatus {
        if (isMinePosition(pos)) {
            return CellOpenStatus.FAIL
        }

        val posQueue = ArrayDeque<Position>().apply {
            this.add(pos)
        }

        while(!posQueue.isEmpty()) {
            val now = posQueue.removeFirst()
            playerBoard[now.y][now.x] = adminBoard[now.y][now.x]
            visitedBoard[now.y][now.x] = VISITED_CELL
            val nearPositions = boardDimensions.getNearPositions(now)
            nearPositions.forEach {
                if (!isMinePosition(it)) {
                    if (adminBoard[it.y][it.x] == NUMBER_INIT_CELL && visitedBoard[it.y][it.x] != VISITED_CELL) {
                        posQueue.add(it)
                    }
                    playerBoard[it.y][it.x] = adminBoard[it.y][it.x]
                }
            }
        }
        return CellOpenStatus.SUCCESS
    }

    private fun isMinePosition(pos: Position): Boolean =
        adminBoard.board[pos.y][pos.x] == GameBoardRenderStrategy.MINE

    private fun mineMarking(gameBoard: RenderedGameBoard): RenderedGameBoard {
        val board = gameBoard.board
        mines.mines.forEach { mine ->
            board[mine.y][mine.x] = GameBoardRenderStrategy.MINE
            boardDimensions.getNearPositions(mine)
                .forEach { if (board[it.y][it.x] != GameBoardRenderStrategy.MINE) board[it.y][it.x]++ }
        }
        return gameBoard
    }
    fun playerBoardRender() = playerBoard.joinToString()

    companion object {
        private const val INIT_CELL = 'C'
        private const val NUMBER_INIT_CELL = '0'
        private const val VISITED_CELL = 'T'
    }
}
