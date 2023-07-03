package minesweeper.domain.board

import minesweeper.domain.position.MineSweeperPositions
import minesweeper.domain.position.Position.Companion.MIN_POSITION

class MineSweeperBoard(
    boardRange: BoardRange,
    mineQuantity: Int,
) {

    private val board: List<MineSweeperPositions>

    init {
        require(mineQuantity >= MIN_MINE_QUANTITY) { "지뢰 개수는 $MIN_MINE_QUANTITY 개 이상이어야 합니다." }
        require(mineQuantity <= boardRange.calculateArea()) { "지뢰 개수는 전체 크기를 초과할 수 없습니다." }

        val minePositions = boardRange.createRandomPositions(mineQuantity)
        val widthPositionRange = IntRange(MIN_POSITION, boardRange.maxWidth())
        val heightPositionRange = IntRange(MIN_POSITION, boardRange.maxHeight())
        board = heightPositionRange.map { yPosition ->
            MineSweeperPositions.create(widthPositionRange, yPosition, minePositions)
        }
    }

    fun rows(): Iterator<MineSweeperPositions> = board.iterator()

    companion object {
        private const val MIN_MINE_QUANTITY = 1
    }
}
