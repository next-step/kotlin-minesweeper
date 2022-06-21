package minesweeper.domain

fun Collection<Coordinate>.toCoordinates() = Coordinates(this.toSet())

data class Coordinates(private val set: Set<Coordinate> = setOf()) : Set<Coordinate> by set {

    operator fun plus(other: Coordinates) = Coordinates(this.set + other.set)

    companion object {
        fun coordinatesInArea(height: Int, width: Int): Coordinates =
            Coordinates(
                (0 until height).map { y ->
                    coordinatesInColumn(y, width)
                }.reduce(Coordinates::plus).toCoordinates()
            )

        private fun coordinatesInColumn(column: Int, length: Int): Coordinates =
            (0 until length).map { x ->
                Coordinate(column, x)
            }.toCoordinates()
    }
}
