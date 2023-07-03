package minesweeper.domain.game

import minesweeper.domain.board.BoardRange
import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.explorer.ExploreResult
import minesweeper.domain.explorer.MineSweeperBoardExplorer
import minesweeper.domain.position.MinePosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class MineSweeperGame(boardRange: BoardRange, mineQuantity: Int) {

    private val board: MineSweeperBoard
    private val boardExplorer: MineSweeperBoardExplorer

    init {
        board = MineSweeperBoard(boardRange = boardRange, mineQuantity = mineQuantity)
        boardExplorer = MineSweeperBoardExplorer()
    }

    fun playGame(
        positionGetter: () -> Position,
        showExploredBoard: (MineSweeperBoard, MineSweeperBoardExplorer) -> Unit,
        showLoseGameMessage: () -> Unit,
        showWinGameMessage: () -> Unit,
    ) {
        do {
            val position = positionGetter()
            val exploreResult = boardExplorer.explore(board = board, startPosition = position)
            showExploreResult(
                exploreResult = exploreResult,
                showExploredBoard = showExploredBoard,
                showLoseGameMessage = showLoseGameMessage,
            )
        } while (isProceedGame(exploreResult))
        if(isAllVisitExcludeMinePositions()) {
            showWinGameMessage()
        }
    }

    private fun isProceedGame(exploreResult: ExploreResult) = exploreResult == ExploreResult.SuccessExplore &&
        !isAllVisitExcludeMinePositions()

    private fun isAllVisitExcludeMinePositions(): Boolean {
        val visitedPositions = boardExplorer.allVisitedPositions()

        val allPositions = board.allPositions()

        val nonVisitEmptyPositions = allPositions
            .filter { !isVisitPosition(visitedPositions = visitedPositions, boardPosition = it) }
            .filter { it !is MinePosition }

        return nonVisitEmptyPositions.isEmpty()
    }

    private fun isVisitPosition(
        visitedPositions: Positions,
        boardPosition: MineSweeperPosition,
    ) = visitedPositions.any { visitedPosition ->
        boardPosition.isSamePosition(
            visitedPosition,
        )
    }

    private fun showExploreResult(
        exploreResult: ExploreResult,
        showExploredBoard: (MineSweeperBoard, MineSweeperBoardExplorer) -> Unit,
        showLoseGameMessage: () -> Unit,
    ) {
        when (exploreResult) {
            is ExploreResult.SuccessExplore -> showExploredBoard(board, boardExplorer)
            is ExploreResult.FailExplore -> showLoseGameMessage()
        }
    }
}
