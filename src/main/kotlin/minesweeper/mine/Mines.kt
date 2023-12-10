package minesweeper.mine

import minesweeper.board.BoardDimensions
import minesweeper.board.GameBoardRenderStrategy
import minesweeper.board.RenderedGameBoard
import minesweeper.position.Position

@JvmInline
value class Mines(
    val mines: Set<Position>
) {
    fun mineMarking(gameBoard: RenderedGameBoard, boardDimensions: BoardDimensions) {
        mines.forEach { mine ->
            gameBoard[mine.y][mine.x] = GameBoardRenderStrategy.MINE
            mine.nearPositions(boardDimensions)
                .forEach { if (gameBoard[it.y][it.x] != GameBoardRenderStrategy.MINE) gameBoard[it.y][it.x]++ }
        }
    }
}
