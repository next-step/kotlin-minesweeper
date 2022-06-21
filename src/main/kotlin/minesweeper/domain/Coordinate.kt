package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {

    init {
        require(x >= 0 && y >= 0) { "x,y 좌표는 0과 같거나 커야한다." }
    }

    companion object {
        fun coordinatesInArea(height: Int, width: Int): List<Coordinate> =
            (0 until height).map { y ->
                (0 until width).map { x ->
                    Coordinate(y, x)
                }
            }.flatten()
    }
}
