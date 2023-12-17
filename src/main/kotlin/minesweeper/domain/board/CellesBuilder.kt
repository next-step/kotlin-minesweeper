package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCount

fun cells(
    block: CellsBuilder.() -> Unit
): Set<Cell> = CellsBuilder().apply(block).build()

class CellsBuilder() {
    private lateinit var positions: Positions

    fun positions(positions: Positions) {
        this.positions = positions
    }

    fun build(): Set<Cell> {
        val mineCountByPositions = positions.mineCountByPosition
        val cells = mineCountByPositions.map { (position, mineCount) ->
            if (positions.isMine(position)) Cell.Mine(position)
            else Cell.Clear(position, MineCount.from(mineCount))
        }.toSet()
        return cells
    }
}
