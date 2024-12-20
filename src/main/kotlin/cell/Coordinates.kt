package cell

class Coordinates(
    private val values: List<Coordinate>,
) {
    fun randomMineCoordinates(mineCount: Int): Map<Coordinate, Cell> = values.shuffled().take(mineCount).let(::toCells)

    private fun toCells(mineCoordinates: List<Coordinate>): Map<Coordinate, Cell> {
        val mineCells = mineCoordinates.map { it to MineCell }
        val blankCells =
            values
                .filterNot(mineCoordinates::contains)
                .map { it to BlankCell }

        return (mineCells + blankCells).toMap()
    }

    companion object {
        private const val MIN_VALUE = 0

        fun of(
            height: Int,
            width: Int,
        ): Coordinates {
            require(height > MIN_VALUE) { "높이는 0 보다 커야 합니다." }
            require(width > MIN_VALUE) { "너비는 0 보다 커야 합니다." }
            return Coordinates(generateCoordinates(height, width))
        }

        private fun generateCoordinates(
            height: Int,
            width: Int,
        ): List<Coordinate> =
            (MIN_VALUE until height).flatMap { y ->
                generateRowCoordinates(y, width)
            }

        private fun generateRowCoordinates(
            y: Int,
            width: Int,
        ): List<Coordinate> =
            (MIN_VALUE until width).map { x ->
                createCoordinate(x, y)
            }

        private fun createCoordinate(
            x: Int,
            y: Int,
        ): Coordinate =
            Coordinate(
                CoordinateValue(x),
                CoordinateValue(y),
            )
    }
}
