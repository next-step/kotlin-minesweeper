package minesweeper.entity

@JvmInline
value class Cells(
    private val _cells: Map<Coordinate, Cell>,
) {
    val cells: Collection<Cell>
        get() = _cells.values

    fun findCell(coordinate: Coordinate): Cell {
        return _cells[coordinate]
            ?: throw IllegalArgumentException("셀을 찾을 수 없습니다: $coordinate")
    }

    fun hasRevealedMine(): Boolean {
        return cells.any { !it.isSafe() && it.isRevealed }
    }

    fun findUnrevealedNeighbors(
        coordinate: Coordinate,
        width: Width,
        height: Height,
    ): List<Cell> {
        return coordinate.findAdjacentCoordinates()
            .filter { it.isWithinBounds(width, height) && isUnrevealedCell(it) }
            .mapNotNull { _cells[it] }
    }

    private fun isUnrevealedCell(coordinate: Coordinate): Boolean {
        return _cells[coordinate]?.isRevealed == false
    }

    companion object {
        fun create(
            allCoordinates: List<Coordinate>,
            mineCoordinates: Set<Coordinate>,
        ): Cells {
            val invalidCoordinates = mineCoordinates - allCoordinates.toSet()
            require(invalidCoordinates.isEmpty()) {
                "지뢰 좌표가 보드 범위를 벗어났습니다"
            }

            val cells =
                allCoordinates.associateWith { coordinate ->
                    when (coordinate) {
                        in mineCoordinates -> Cell.Mine(coordinate)
                        else -> Cell.Empty(coordinate)
                    }
                }
            return Cells(cells)
        }
    }
}
