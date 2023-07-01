package domain

class Row(
    private val cells: List<Cell>
) : List<Cell> by cells {
    companion object {
        fun of(width: Int, row: Int, mineCoordinates: Set<Coordinate>): Row {
            val cells = (Coordinate.COL_START_POSITION..width).map { col ->
                val isMine = mineCoordinates.contains(Coordinate(row, col))
                Cell.of(isMine)
            }
            return Row(cells)
        }
    }
}
