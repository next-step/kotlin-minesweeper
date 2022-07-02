package domain

sealed class Cell(private val coordinate: Coordinate) {

    val x = coordinate.x

    val y = coordinate.y

    fun isAdjacentTo(cell: Cell): Boolean = coordinate.isAdjacentTo(cell.coordinate)
}

class Mine(coordinate: Coordinate) : Cell(coordinate)

class Empty(coordinate: Coordinate) : Cell(coordinate) {
    private var isOpen = false

    val opened: Boolean
        get() = isOpen

    fun open() {
        isOpen = true
    }
}
