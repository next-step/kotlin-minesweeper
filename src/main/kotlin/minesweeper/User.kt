package minesweeper

import minesweeper.board.BoardDimensions
import minesweeper.board.GameBoardRenderStrategy
import minesweeper.board.RenderedGameBoard
import minesweeper.mine.Mines
import minesweeper.position.Position

class AdminBoard(
    private val board: RenderedGameBoard
) {
    constructor(
        renderStrategy: GameBoardRenderStrategy,
        boardDimensions: BoardDimensions,
        mines: Mines
    ): this(
        renderStrategy(boardDimensions, NUMBER_INIT_CELL)
            .apply { mines.mineMarking(this, boardDimensions) }
    )

    fun isMinePosition(position: Position): Boolean =
        this.board[position.y][position.x] == GameBoardRenderStrategy.MINE

    fun isCleanCell(position: Position): Boolean =
        this.board[position.y][position.x] == NUMBER_INIT_CELL

    fun getCell(position: Position) = board[position.y][position.x]

    companion object {
        private const val NUMBER_INIT_CELL = '0'
    }
}

class PlayerBoard(
    private val board: RenderedGameBoard,
    private val visitedBoard: RenderedGameBoard
) {
    constructor(
        renderStrategy: GameBoardRenderStrategy,
        boardDimensions: BoardDimensions
    ): this(
        renderStrategy(boardDimensions, RenderedGameBoard.INIT_CELL),
        renderStrategy(boardDimensions, RenderedGameBoard.INIT_CELL)
    )

    fun boardRender() = board.joinToString()

    fun visit(position: Position) {
        visitedBoard[position.y][position.x] = VISITED_CELL
    }

    fun isVisited(position: Position) =
        visitedBoard[position.y][position.x] == VISITED_CELL

    fun setCell(position: Position, cell: Char) {
        board[position.y][position.x] = cell
    }

    companion object {
        private const val VISITED_CELL = 'T'
    }
}
