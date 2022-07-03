package domain

import domain.vo.Point

sealed class Cell(private val coordinate: Coordinate) {

    private var isOpen = false

    val x: Point
        get() = coordinate.x

    val y: Point
        get() = coordinate.y

    val opened: Boolean
        get() = isOpen

    fun open() {
        isOpen = true
    }

    fun isAdjacentTo(cell: Cell): Boolean = coordinate.isAdjacentTo(cell.coordinate)

    fun equalsBy(coordinate: Coordinate): Boolean = this.coordinate == coordinate
}

class Mine(coordinate: Coordinate) : Cell(coordinate)

class Empty(coordinate: Coordinate) : Cell(coordinate)
