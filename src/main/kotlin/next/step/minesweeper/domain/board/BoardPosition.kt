package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.NearPositionDelta
import next.step.minesweeper.domain.position.Position

data class BoardPosition(private val position: Position, private val area: BoardArea) {

    fun x(): Int = position.x

    fun y(): Int = position.y

    fun near(): BoardPositions =
        BoardPositions(
            NearPositionDelta.values()
                .map { BoardPosition(Position(position.x + it.dx, position.y + it.dy), area) }
                .toSet(),
        )

    companion object {
        fun of(x: Int, y: Int, area: BoardArea): BoardPosition = BoardPosition(Position(x, y), area)
    }
}
