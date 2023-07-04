package minesweeper.domain.explorer

import minesweeper.domain.board.MineSweeperBoard
import minesweeper.domain.position.EmptyPosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.Position

class MineSweeperBoardExplorer {

    private val visitQueue: VisitQueue = VisitQueue()

    fun explore(board: MineSweeperBoard, startPosition: Position): ExploreResult {
        if (board.isMinePosition(position = startPosition)) {
            return ExploreResult.FailExplore
        }
        board.visit(position = startPosition)
        visitQueue.push(startPosition)

        while (!visitQueue.isEmpty()) {
            val position = visitQueue.pop()
            visitAround(position = position, board = board)
        }
        return ExploreResult.SuccessExplore
    }

    private fun visitAround(position: Position, board: MineSweeperBoard) {
        val mineSweeperPosition = board.find(position)
        if (isVisitAroundPosition(mineSweeperPosition)) {
            val visitedPositions = board.visitAround(position)
            visitedPositions.forEach(visitQueue::push)
        }
    }

    private fun isVisitAroundPosition(mineSweeperPosition: MineSweeperPosition): Boolean =
        mineSweeperPosition is EmptyPosition &&
            mineSweeperPosition.calculateAroundMineQuantity() == AROUND_MINE_QUANTITY_OF_AROUND_VISIT_TARGET

    companion object {
        private const val AROUND_MINE_QUANTITY_OF_AROUND_VISIT_TARGET = 0
    }
}
