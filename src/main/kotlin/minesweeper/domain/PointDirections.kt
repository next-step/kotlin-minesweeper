package minesweeper.domain

import minesweeper.model.Point

enum class PointDirections(
    val positionX: Int, val positionY: Int
) {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT_UP(-1, -1),
    LEFT_DOWN(-1, 1),
    RIGHT_UP(1, -1),
    RIGHT_DOWN(1, 1);

    companion object {
        fun neighbors(point: Point, maxPoint: Point) = values().mapNotNull {
            val neighbor = point + Point(it.positionX, it.positionY)
            if (neighbor.x < 0 || neighbor.x >= maxPoint.x) return@mapNotNull null
            if (neighbor.y < 0 || neighbor.y >= maxPoint.y) return@mapNotNull null
            neighbor
        }
    }
}
