package minesweeper

import minesweeper.board.GameBoardRenderStrategy
import minesweeper.position.Position
import minesweeper.board.RenderedGameBoard
import minesweeper.board.BoardDimensions
import minesweeper.mine.Mines

class MinesweeperBoard(
    renderStrategy: GameBoardRenderStrategy,
    private val boardDimensions: BoardDimensions,
    private val mines: Mines
) {
    private val adminBoard: RenderedGameBoard = mineMarking(renderStrategy(boardDimensions, NUMBER_INIT_CELL))
    private val playerBoard: RenderedGameBoard = renderStrategy(boardDimensions, INIT_CELL)
    private val visitedBoard: RenderedGameBoard = renderStrategy(boardDimensions, INIT_CELL)

    fun openCell(position: Position): CellOpenStatus {
        if (isMinePosition(position)) {
            return CellOpenStatus.FAIL
        }

        val positionQueue = ArrayDeque<Position>().apply {
            this.add(position)
        }

        while(!positionQueue.isEmpty()) {
            val now = positionQueue.removeFirst()
            playerBoard[now.y][now.x] = adminBoard[now.y][now.x]
            visitedBoard[now.y][now.x] = VISITED_CELL
            val nearPositions = now.nearPositions(boardDimensions)
            nearPositions.forEach {
                if (!isMinePosition(it)) {
                    if (adminBoard[it.y][it.x] == NUMBER_INIT_CELL && visitedBoard[it.y][it.x] != VISITED_CELL) {
                        positionQueue.add(it)
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
            mine.nearPositions(boardDimensions)
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
