package minesweeper.domain.board

import minesweeper.domain.position.*

object MineSweeperBoardGenerator {

    private const val MIN_POSITION = 1
    fun create(boardRange: BoardRange, minePositions: Positions): MineSweeperBoard {
        val widthPositionRange = IntRange(MIN_POSITION, boardRange.maxWidth())
        val heightPositionRange = IntRange(MIN_POSITION, boardRange.maxHeight())
        val board = heightPositionRange.map { yPosition ->
            MineSweeperPositions.create(widthPositionRange, yPosition, minePositions)
        }
        return MineSweeperBoard(board = board, boardRange = boardRange, mineQuantity = minePositions.size)
    }

}
