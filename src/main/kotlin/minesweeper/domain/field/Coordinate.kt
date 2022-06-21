package minesweeper.domain.field

data class Coordinate(
    val x: CoordinateValue,
    val y: CoordinateValue
) {
    fun findAround(): List<Coordinate> = listOf(east(), west(), south(), north(), northEast(), northWest(), southEast(), southWest()).filter { it.x.value >= 0 && it.y.value >= 0 }

    private fun east(): Coordinate = Coordinate(x + CoordinateValue.move(), y)

    private fun west(): Coordinate = Coordinate(x - CoordinateValue.move(), y)

    private fun south(): Coordinate = Coordinate(x, y + CoordinateValue.move())

    private fun north(): Coordinate = Coordinate(x, y - CoordinateValue.move())

    private fun northEast(): Coordinate = Coordinate(x + CoordinateValue.move(), y - CoordinateValue.move())

    private fun northWest(): Coordinate = Coordinate(x - CoordinateValue.move(), y - CoordinateValue.move())

    private fun southEast(): Coordinate = Coordinate(x + CoordinateValue.move(), y + CoordinateValue.move())

    private fun southWest(): Coordinate = Coordinate(x - CoordinateValue.move(), y + CoordinateValue.move())
}
