package minesweeper.domain

fun Collection<Coordinate>.toCoordinates() = Coordinates(this.toSet())

data class Coordinates(private val set: Set<Coordinate> = setOf()) : Set<Coordinate> by set {

    operator fun plus(other: Coordinates) = Coordinates(this.set + other.set)

    fun containsCount(other: Coordinates) = count(other::contains)

    companion object {
        fun coordinatesInArea(area: Area): Coordinates =
            (0 until area.height).map { y ->
                coordinatesInColumn(y, area.width)
            }.reduce(Coordinates::plus).sorted().toCoordinates()

        private fun coordinatesInColumn(column: Int, length: Int): Coordinates =
            (0 until length).map { x ->
                Coordinate(column, x)
            }.toCoordinates()
    }
}
