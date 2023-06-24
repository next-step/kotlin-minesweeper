package minesweeper.domain

data class Coordinate(val x: Int, val y: Int) {

    operator fun compareTo(other: Coordinate): Int = compareValuesBy(
        a = this,
        b = other,
        { x },
        { y },
    )
}
