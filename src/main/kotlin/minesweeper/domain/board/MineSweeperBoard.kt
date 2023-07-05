package minesweeper.domain.board

import minesweeper.domain.explorer.VisitQueue
import minesweeper.domain.position.EmptyPosition
import minesweeper.domain.position.MinePosition
import minesweeper.domain.position.MineSweeperPosition
import minesweeper.domain.position.MineSweeperPositions
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class MineSweeperBoard(
    boardRange: BoardRange,
    mineQuantity: Int,
) {

    private val board: List<MineSweeperPositions>

    init {
        require(mineQuantity >= MIN_MINE_QUANTITY) { "지뢰 개수는 $MIN_MINE_QUANTITY 개 이상이어야 합니다." }
        require(mineQuantity <= boardRange.calculateArea()) { "지뢰 개수는 전체 크기를 초과할 수 없습니다." }

        val minePositions = Positions.createRandomPositions(
            minRandomPositionSize = mineQuantity,
            boardRange = boardRange,
        )
        val widthPositionRange = boardRange.widthRange()
        val heightPositionRange = boardRange.heightRange()
        board = heightPositionRange.map { yPosition ->
            MineSweeperPositions.create(widthPositionRange, yPosition, minePositions)
        }
    }

    fun rows(): Iterator<MineSweeperPositions> = board.iterator()

    fun isAllOpenWithoutMinePositions(): Boolean {
        val nonVisitEmptyPositions = board.flatten()
            .filter { !it.isVisit() }
            .filterIsInstance<EmptyPosition>()
        return nonVisitEmptyPositions.isEmpty()
    }

    fun isMinePosition(position: Position): Boolean {
        if (!this.containsPosition(position)) {
            return false
        }
        val mineSweeperPosition = find(position)
        return mineSweeperPosition is MinePosition
    }

    fun open(startPosition: Position) {
        val visitQueue = VisitQueue()
        visitQueue.push(position = startPosition)
        visit(position = startPosition)

        while (!visitQueue.isEmpty()) {
            val position = visitQueue.pop()
            val openedPositions = openAround(position = position)
            visitQueue.pushAll(openedPositions)
        }
    }

    private fun openAround(position: Position): Positions {
        val mineSweeperPosition = find(position)
        if (mineSweeperPosition.isExistsMinesAround()) {
            return Positions(emptyList())
        }

        val aroundPositions = position.aroundPositions()
        val visitedPositions = aroundPositions
            .filter { isOpenTargetPosition(position = it) }
            .map { visitPosition ->
                this.visit(visitPosition)
                visitPosition
            }
        return Positions(visitedPositions)
    }

    private fun visit(position: Position): MineSweeperPosition {
        val mineSweeperPosition = this.find(position)
        mineSweeperPosition.visit()
        return mineSweeperPosition
    }

    private fun find(position: Position): MineSweeperPosition {
        validatePosition(position)
        val y = position.convertBoardYPositionIndex()
        val x = position.convertBoardXPositionIndex()
        return board[y][x]
    }

    private fun isOpenTargetPosition(position: Position): Boolean {
        if (!this.containsPosition(position)) {
            return false
        }
        val mineSweeperPosition = this.find(position)
        return mineSweeperPosition.isVisitablePosition()
    }

    private fun validatePosition(position: Position) {
        require(this.containsPosition(position)) { "범위를 벗어난 위치입니다." }
    }

    fun containsPosition(position: Position): Boolean {
        val y = position.convertBoardYPositionIndex()
        val x = position.convertBoardXPositionIndex()
        return try {
            board[y][x]
            true
        } catch (exception: Exception) {
            false
        }
    }

    companion object {
        private const val MIN_MINE_QUANTITY = 1
    }
}
