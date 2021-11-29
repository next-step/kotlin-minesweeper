package minesweeper.domain.cell

class CellState(val value: Int = 0, val cellType: CellType) {

    companion object {
        fun from(currentCell: Cell, mineCells: Cells): CellState {
            if (mineCells.contains(currentCell)) {
                return CellState(IS_MINE_VALUE, CellType.IS_MINE)
            }
            val count = currentCell.countingAdjacentMines(mineCells)
            return CellState(count, CellType.NOT_MINE)
        }

        private const val IS_MINE_VALUE = -1
    }
}
