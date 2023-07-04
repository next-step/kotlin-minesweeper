package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.position.NearPositionDelta

data class BoardPosition(val x: Int, val y: Int) {

    fun near(): BoardPositions =
        BoardPositions(
            NearPositionDelta.values()
                .map { BoardPosition(x + it.dx, y + it.dy) }
                .toSet(),
        )
}
