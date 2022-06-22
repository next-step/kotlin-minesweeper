package minesweeper.domain

enum class Direction(val x: Int, val y: Int) {
    LEFT_TOP(-1, -1),
    LEFT_CENTER(-1, 0),
    LEFT_BOTTOM(-1, 1),
    CENTER_TOP(0, -1),
    CENTER_BOTTOM(0, 1),
    RIGHT_TOP(1, -1),
    RIGHT_CENTER(1, 0),
    RIGHT_BOTTOM(1, 1)
}

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    init {
        require(x >= 0 && y >= 0) { "x,y 좌표는 0과 같거나 커야한다." }
    }

    fun aroundCoordinates(): Coordinates {
        return Direction.values().mapNotNull {
            val movedX = x + it.x
            val movedY = y + it.y

            if (movedX < 0 || movedY < 0) null
            else Coordinate(movedX, movedY)
        }.toCoordinates()
    }

    override fun compareTo(other: Coordinate): Int {
        return when (val compareY = y.compareTo(other.y)) {
            0 -> x.compareTo(other.x)
            else -> compareY
        }
    }
}
