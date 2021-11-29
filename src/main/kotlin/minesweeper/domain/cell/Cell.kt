package minesweeper.domain.cell

import minesweeper.domain.position.Position

data class Cell constructor(val position: Position) {

    lateinit var state: CellState
        private set

    fun updateCellStatus(mineCells: Cells) {
        state = CellState.from(this, mineCells)
    }

    fun countingAdjacentMines(mineCells: Cells) =
        position.countingAdjacentMines(mineCells)

    companion object {
        fun of(position: Position): Cell = Cell(position)
    }
}
