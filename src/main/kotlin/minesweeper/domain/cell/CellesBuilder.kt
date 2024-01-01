package minesweeper.domain.cell

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

fun cells(
    block: CellsBuilder.() -> Unit
): Map<Position, Cell> = CellsBuilder().apply(block).build()

class CellsBuilder() {
    private lateinit var positions: Positions

    fun positions(positions: Positions) {
        this.positions = positions
    }

    fun build(): Map<Position, Cell> {
        val mineCountByPositions = positions.mineCountByPosition
        return mineCountByPositions.map { (position, mineCount) ->
            if (positions.isMine(position)) position to Cell.ofMine(position)
            else position to Cell.ofClear(position, mineCount)
        }.toMap()
    }
}
