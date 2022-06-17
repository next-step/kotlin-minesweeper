package minesweeper.domain

class Cells(val cells: List<Cell>) {

    companion object {
        fun of(positions: List<Position>, minePositions: List<Position>): Cells {
            return positions.map { position ->
                Cell.of(position, minePositions.cellType(position))
            }.let { Cells(it) }
        }

        private fun List<Position>.cellType(position: Position): CellType {
            return if (contains(position)) CellType.MINE else CellType.NON_MINE
        }
    }
}
