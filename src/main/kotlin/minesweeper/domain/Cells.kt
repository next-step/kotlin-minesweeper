package minesweeper.domain

class Cells(val cells: List<Cell>) : List<Cell> by cells {

    fun groupByPositionX(): List<List<Cell>> {
        return cells.groupBy { it.position.y }.map { it.value }
    }

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells {
            return positions
                .onEach { it.setNearPositions(positions) }
                .map { Cell.of(it, minePositions) }
                .let { Cells(it) }
        }
    }
}
