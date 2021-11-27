package minesweeper.domain.cell

import minesweeper.domain.position.Positions

class Cells(private val cells: List<Cell>) : List<Cell> by cells {

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells =
            positions.map {
                Cell.of(it, minePositions)
            }.run {
                Cells(this)
            }
    }
}
