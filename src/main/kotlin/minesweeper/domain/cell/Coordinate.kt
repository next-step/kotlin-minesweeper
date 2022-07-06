package minesweeper.domain.cell

data class Coordinate(
    val x: CoordinateValue,
    val y: CoordinateValue,
) {
    fun around(): List<Coordinate> = CoordinateDirection.aroundCoordinates(coordinate = this)
}
