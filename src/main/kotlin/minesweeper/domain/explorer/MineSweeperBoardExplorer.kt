package minesweeper.domain.explorer

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.EmptyPosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import minesweeper.domain.position.VisitedPositions

class MineSweeperBoardExplorer {

    private val visitedPositions: VisitedPositions = VisitedPositions()
    private val visitQueue: VisitQueue = VisitQueue()

    fun explore(board: MineSweeperBoard, startPosition: Position): ExploreResult {
        if (!isEnqueuePosition(position = startPosition, board = board)) {
            return ExploreResult.FailExplore
        }
        visitedPositions.visit(startPosition)
        visitQueue.push(startPosition)

        while (!visitQueue.isEmpty()) {
            val position = visitQueue.pop()
            visitAround(position = position, board = board)
        }

        return ExploreResult.SuccessExplore
    }

    private fun visitAround(position: Position, board: MineSweeperBoard) {
        val mineSweeperPosition = board.find(position)
        if (!isVisitAroundPosition(mineSweeperPosition)) {
            return
        }

        val aroundPositions = position.aroundPositions()
        aroundPositions
            .filter { isEnqueuePosition(position = it, board = board) }
            .forEach {
                visitedPositions.visit(it)
                visitQueue.push(it)
            }
    }

    private fun isVisitAroundPosition(mineSweeperPosition: MineSweeperPosition): Boolean =
        mineSweeperPosition is EmptyPosition &&
            mineSweeperPosition.calculateAroundMineQuantity() == AROUND_MINE_QUANTITY_OF_AROUND_VISIT_TARGET

    private fun isEnqueuePosition(position: Position, board: MineSweeperBoard): Boolean {
        if (!board.containsPosition(position)) {
            return false
        }
        val mineSweeperPosition = board.find(position)
        return mineSweeperPosition is EmptyPosition && !visitedPositions.isVisit(position)
    }

    fun allVisitedPositions(): Positions = visitedPositions.allVisitedPositions()

    fun isVisit(position: MineSweeperPosition): Boolean {
        val visitedPositions = this.allVisitedPositions()
        return visitedPositions.any { position.isSamePosition(it) }
    }

    companion object {
        private const val AROUND_MINE_QUANTITY_OF_AROUND_VISIT_TARGET = 0
    }
}
