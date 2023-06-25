package minesweeper.domain.finder

import minesweeper.domain.MinePoint
import minesweeper.domain.Point

typealias movePoint = (MinePoint) -> Point

enum class AdjacentPoints(val movePoint: movePoint) {
    // 북서쪽
    NORTHWESTWARD({ Point(x = it.x - 1, y = it.y - 1) }),

    // 북쪽
    NORTH({ Point(x = it.x, y = it.y - 1) }),

    // 북동쪽
    NORTHEASTWARD({ Point(x = it.x + 1, y = it.y - 1) }),

    // 동쪽
    EAST({ Point(x = it.x + 1, y = it.y) }),

    // 남동쪽
    SOUTHEASTWARD({ Point(x = it.x + 1, y = it.y + 1) }),

    // 남쪽
    SOUTH({ Point(x = it.x, y = it.y + 1) }),

    // 남서쪽
    SOUTHWEST({ Point(x = it.x - 1, y = it.y + 1) }),

    // 서쪽
    WEST({ Point(x = it.x - 1, y = it.y) });
}
