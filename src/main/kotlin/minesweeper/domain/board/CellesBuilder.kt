package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark

fun cells(
    block: CellsBuilder.() -> Unit
): Set<Cell> = CellsBuilder().apply(block).build()

class CellsBuilder() {
    private lateinit var positions: Positions

    fun positions(positions: Positions) {
        this.positions = positions
    }

    fun build(): Set<Cell> {
        val adjacentMineCountByPositions = positions.adjacentMineCountByPosition
        val cells = adjacentMineCountByPositions.map { (position, mineCount) ->
            if (positions.isMine(position)) Cell(position, CellMark.MINE)
            else Cell(position, CellMark.from(mineCount))
        }.toSet()
        return cells
    }
}
