package minesweeper.domain.finder

import minesweeper.domain.Point
import minesweeper.domain.SymbolPoint

typealias movePoint = (SymbolPoint) -> Point

private const val MOVING_POINT = 1

enum class AdjacentPoints(val movePoint: movePoint) {
    // 북서쪽
    NORTHWESTWARD({ Point(x = it.x - MOVING_POINT, y = it.y - MOVING_POINT) }),

    // 북쪽
    NORTH({ Point(x = it.x, y = it.y - MOVING_POINT) }),

    // 북동쪽
    NORTHEASTWARD({ Point(x = it.x + MOVING_POINT, y = it.y - MOVING_POINT) }),

    // 동쪽
    EAST({ Point(x = it.x + MOVING_POINT, y = it.y) }),

    // 남동쪽
    SOUTHEASTWARD({ Point(x = it.x + MOVING_POINT, y = it.y + MOVING_POINT) }),

    // 남쪽
    SOUTH({ Point(x = it.x, y = it.y + MOVING_POINT) }),

    // 남서쪽
    SOUTHWEST({ Point(x = it.x - MOVING_POINT, y = it.y + MOVING_POINT) }),

    // 서쪽
    WEST({ Point(x = it.x - MOVING_POINT, y = it.y) });
}
