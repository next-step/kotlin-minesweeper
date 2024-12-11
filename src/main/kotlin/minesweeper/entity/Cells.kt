package minesweeper.entity

@JvmInline
value class Cells(
    val cells: List<Cell>,
) {
    fun findCell(coordinate: Coordinate): Cell {
        return cells.find { it.matches(coordinate) }
            ?: throw IllegalArgumentException("셀을 찾을 수 없습니다: $coordinate")
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
                allCoordinates.map { coordinate ->
                    when (coordinate) {
                        in mineCoordinates -> Cell.Mine(coordinate)
                        else -> Cell.Empty(coordinate)
                    }
                }
            return Cells(cells)
        }
    }
}
