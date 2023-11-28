package minesweeper.model.point

import minesweeper.model.board.BoardLimit

data class Coordinate(
    private val vertical: Vertical,
    private val horizontal: Horizontal,
) {
    fun moveTo(delta: Delta): Coordinate {
        return Coordinate(
            vertical = vertical.moveTo(delta.verticalDelta),
            horizontal = horizontal.moveTo(delta.horizontalDelta)
        )
    }

    fun movePossible(delta: Delta, limit: BoardLimit): Boolean {
        return movePossibleVertical(delta, limit) &&
            movePossibleHorizontal(delta, limit)
    }

    private fun movePossibleHorizontal(delta: Delta, boardLimit: BoardLimit) =
        horizontal.movePossible(
            delta = delta.horizontalDelta,
            limit = boardLimit.horizontalLimit
        )

    private fun movePossibleVertical(delta: Delta, boardLimit: BoardLimit) =
        vertical.movePossible(
            delta = delta.verticalDelta,
            limit = boardLimit.verticalLimit
        )

    companion object {
        fun of(vertical: Int, horizontal: Int): Coordinate {
            return Coordinate(Vertical(vertical), Horizontal(horizontal))
        }
    }
}
