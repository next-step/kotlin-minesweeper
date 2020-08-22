package minesweeper.domain

class Board(width: Int, length: Int, mines: Int = 0) {
    private val coordinates = Coordinates(width, length)
    private val points = Points(coordinates, coordinates.makeMineCoordinates(mines))

    fun findPoint(x: Int, y: Int): Point = points.findPoint(x, y)

    fun countMine(): Int = points.countMine()

    fun forEachPoints(f: (point: Point) -> Unit) {
        points.forEach { f(it) }
    }
}
