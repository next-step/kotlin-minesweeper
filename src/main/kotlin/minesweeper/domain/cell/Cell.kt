package minesweeper.domain.cell

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

data class Cell(val position: Position, val state: CellState) {

    companion object {
        fun of(position: Position, minePositions: Positions): Cell =
            Cell(position, CellState.from(position, minePositions))
    }
}
