package minesweeper.domain.cell

class Coordinates(
    val values: List<Coordinate>,
) {
    fun shuffledAndTake(count: Int): Coordinates = Coordinates(
        values.shuffled()
            .take(count)
    )

    fun mineAt(mineCoordinates: Coordinates): List<Cell> = values.map {
        if (it in mineCoordinates.values) {
            Cell(it, Mine)
        } else {
            Cell(it, Land)
        }
    }

    companion object {
        private const val START_INDEX = 0

        fun from(height: Int, width: Int): Coordinates = Coordinates(
            (START_INDEX until height).flatMap { xValue ->
                (START_INDEX until width).map { yValue ->
                    Coordinate(
                        y = CoordinateValue(value = yValue),
                        x = CoordinateValue(value = xValue)
                    )
                }
            }
        )
    }
}
