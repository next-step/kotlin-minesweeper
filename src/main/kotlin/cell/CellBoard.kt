package cell

class CellBoard(
    private val _cells: Map<Coordinate, Cell>,
) {
    val cellCount: Int
        get() = _cells.size

    val mineCount: Int
        get() = _cells.values.count { it is MineCell }

    val cells: Map<Coordinate, Cell>
        get() = _cells.toMap()

    companion object {
        private const val MIN_VALUE = 0

        fun of(
            height: Length,
            width: Length,
            mineCount: Count,
        ): CellBoard {
            val coordinates = Coordinates.of(height, width)
            val cellsWithMine = coordinates.randomMineCoordinates(mineCount)

            return CellBoard(cellsWithMine)
        }
    }
}
