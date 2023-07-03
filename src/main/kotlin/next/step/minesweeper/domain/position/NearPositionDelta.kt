package next.step.minesweeper.domain.position

import next.step.minesweeper.domain.board.BoardArea

enum class NearPositionDelta(val dx: Int, val dy: Int) {
    TOP_LEFT(-1, -1),
    TOP_CENTER(0, -1),
    TOP_RIGHT(1, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    BOTTOM_LEFT(-1, 1),
    BOTTOM_CENTER(0, 1),
    BOTTOM_RIGHT(1, 1),
    ;

    companion object {

        fun nearInArea(x: Int, y: Int, area: BoardArea): List<Position> =
            NearPositionDelta.values().map { Position(x + it.dx, y + it.dy) }.filter { it in area }
    }
}
