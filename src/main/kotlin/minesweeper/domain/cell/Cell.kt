package minesweeper.domain.cell

import minesweeper.domain.position.Position

data class Cell constructor(val position: Position) {

    lateinit var state: CellState
        private set

    fun isOpenedMineCell(): Boolean = state.isOpenedMine()

    fun isHiddenCell(): Boolean = state.isHidden

    fun isNotMineCell(): Boolean = state.isNotMineCell()

    fun openCell() {
        state.isVisible()
    }

    fun getCellAdjacentCount(): Int = state.value

    fun updateCellStatus(mineCells: Cells) {
        state = CellState.from(this, mineCells)
    }

    fun isContainsAdjacentPositions(otherPosition: Position): Boolean =
        this.position.containsAdjacentPositions(otherPosition)

    fun countingAdjacentMines(mineCells: Cells) = position.countingAdjacentMines(mineCells)

    companion object {
        fun of(position: Position): Cell = Cell(position)
    }
}
