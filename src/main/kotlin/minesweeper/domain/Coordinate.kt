package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) : Comparable<Coordinate> {

    init {
        require(x >= 0 && y >= 0) { "x,y 좌표는 0과 같거나 커야한다." }
    }

    fun aroundCoordinates(): Coordinates {
        return Direction.values().mapNotNull {
            val movedX = x + it.x
            val movedY = y + it.y

            if (movedX < 0 || movedY < 0) return@mapNotNull null
            Coordinate(movedX, movedY)
        }.toCoordinates()
    }

    override fun compareTo(other: Coordinate): Int {
        return when (val compareY = y.compareTo(other.y)) {
            0 -> x.compareTo(other.x)
            else -> compareY
        }
    }
}
