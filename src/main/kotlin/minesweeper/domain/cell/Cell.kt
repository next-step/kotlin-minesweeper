package minesweeper.domain.cell

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class Cell private constructor(val position: Position) {

    lateinit var state: CellState
        private set

    companion object {
        fun of(position: Position, minePositions: Positions): Cell =
            Cell(position).apply {
                this.state = CellState.from(position, minePositions)
            }
    }
}
