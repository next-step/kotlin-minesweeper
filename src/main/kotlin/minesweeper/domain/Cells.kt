package minesweeper.domain

class Cells(val cells: List<Cell>) : List<Cell> by cells {

    fun groupByPositionX(): List<List<Cell>> {
        return cells.groupBy { it.position.y }.map { it.value }
    }

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells {
            return positions.map { position ->
                position.setNearPositions(positions)
                Cell.of(position, minePositions, minePositions.cellType(position))
            }.let { Cells(it) }
        }

        private fun List<Position>.cellType(position: Position): CellType {
            return if (contains(position)) CellType.MINE else CellType.NON_MINE
        }
    }
}
