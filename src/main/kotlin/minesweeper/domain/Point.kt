package minesweeper.domain

data class Point(val value: Int) {
    operator fun plus(x: Point): Point {
        return Point(value + x.value)
    }
}
