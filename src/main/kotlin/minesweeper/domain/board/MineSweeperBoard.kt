package minesweeper.domain.board

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

    fun find(position: Position): MineSweeperPosition {
        validatePosition(position)
        val y = position.convertBoardYPositionIndex()
        val x = position.convertBoardXPositionIndex()
        return board[y][x]
    }

    fun visit(position: Position) {
        validatePosition(position)
        val y = position.convertBoardYPositionIndex()
        val x = position.convertBoardXPositionIndex()
        board[y][x].visit()
    }

    fun isMinePosition(position: Position): Boolean {
        if (!this.containsPosition(position)) {
            return false
        }
        val mineSweeperPosition = find(position)
        return mineSweeperPosition is MinePosition
    }

    fun isAllVisitPositionsWithoutMinePositions(): Boolean {
        val allPositions = MineSweeperPositions(board.map { it }.flatten())
        val nonVisitPositions = allPositions.count { !it.isVisit() && it is EmptyPosition }
        return nonVisitPositions == VISITED_POSITION_COUNT_WITHOUT_MINE_POSITIONS
    }

    fun visitAround(position: Position): Positions {
        val aroundPositions = position.aroundPositions()
        val visitedPositions = aroundPositions
            .filter { isVisitTarget(position = it) }
            .map {
                this.visit(it)
                it
            }
        return Positions(visitedPositions)
    }

    private fun isVisitTarget(position: Position): Boolean {
        if (!this.containsPosition(position)) {
            return false
        }
        val mineSweeperPosition = this.find(position)
        return mineSweeperPosition is EmptyPosition && !mineSweeperPosition.isVisit()
    }

    private fun validatePosition(position: Position) {
        require(this.containsPosition(position)) { "범위를 벗어난 위치입니다." }
    }

    companion object {
        private const val MIN_MINE_QUANTITY = 1
        private const val VISITED_POSITION_COUNT_WITHOUT_MINE_POSITIONS = 0
    }
}
