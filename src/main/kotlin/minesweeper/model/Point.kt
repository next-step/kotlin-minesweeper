package minesweeper.model

data class Point(val x: Int, val y: Int) {
    operator fun plus(point: Point): Point = Point(x + point.x, y + point.y)

    companion object {
        const val MIN_VALUE = 0
    }
}
