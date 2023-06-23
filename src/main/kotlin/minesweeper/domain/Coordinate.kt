package minesweeper.domain

data class Coordinate(val x: Point, val y: Point) {

    operator fun compareTo(other: Coordinate): Int = compareValuesBy(
        a = this,
        b = other,
        { it.x.value },
        { it.y.value },
    )
}
