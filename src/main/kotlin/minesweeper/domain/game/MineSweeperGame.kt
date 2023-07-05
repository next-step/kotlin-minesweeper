package minesweeper.domain.game

import minesweeper.domain.board.BoardRange
import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.explorer.ExploreResult
import minesweeper.domain.explorer.MineSweeperBoardExplorer
import minesweeper.domain.position.Position

class MineSweeperGame(boardRange: BoardRange, mineQuantity: Int) {

    private val board: MineSweeperBoard
    private val boardExplorer: MineSweeperBoardExplorer

    init {
        board = MineSweeperBoard(boardRange = boardRange, mineQuantity = mineQuantity)
        boardExplorer = MineSweeperBoardExplorer()
    }

    fun playGame(
        positionGetter: () -> Position,
        showExploredBoard: (MineSweeperBoard) -> Unit,
        showLoseGameMessage: () -> Unit,
        showWinGameMessage: () -> Unit,
    ) {
        do {
            val exploreResult = proceedGame(positionGetter, showExploredBoard, showLoseGameMessage)
        } while (isProceedGame(exploreResult))

        winGame(showWinGameMessage)
    }

    private fun proceedGame(
        positionGetter: () -> Position,
        showExploredBoard: (MineSweeperBoard) -> Unit,
        showLoseGameMessage: () -> Unit,
    ): ExploreResult {
        val position = getBoardRangePosition(positionGetter)
        val exploreResult = boardExplorer.explore(board = board, startPosition = position)
        showExploreResult(
            exploreResult = exploreResult,
            showExploredBoard = showExploredBoard,
            showLoseGameMessage = showLoseGameMessage,
        )
        return exploreResult
    }

    private fun isProceedGame(exploreResult: ExploreResult) = exploreResult == ExploreResult.SuccessExplore &&
        !board.isAllOpenWithoutMinePositions()

    private fun getBoardRangePosition(positionGetter: () -> Position): Position {
        var position: Position?
        do {
            position = positionGetter()
        } while (!board.containsPosition(position!!))
        return position
    }

    private fun showExploreResult(
        exploreResult: ExploreResult,
        showExploredBoard: (MineSweeperBoard) -> Unit,
        showLoseGameMessage: () -> Unit,
    ) {
        when (exploreResult) {
            is ExploreResult.SuccessExplore -> showExploredBoard(board)
            is ExploreResult.FailExplore -> showLoseGameMessage()
        }
    }

    private fun winGame(showWinGameMessage: () -> Unit) {
        if (board.isAllOpenWithoutMinePositions()) {
            showWinGameMessage()
        }
    }
}
