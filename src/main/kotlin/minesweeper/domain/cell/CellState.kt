package minesweeper.domain.cell

class CellState(
    val value: Int,
    var isHidden: Boolean = true,
    val cellType: CellType
) {

    fun isOpenedMine(): Boolean = !isHidden && cellType == CellType.IS_MINE

    fun isNotMineCell(): Boolean = cellType == CellType.NOT_MINE

    fun isVisible() {
        isHidden = false
    }

    companion object {
        fun from(currentCell: Cell, mineCells: Cells): CellState {
            if (mineCells.contains(currentCell)) {
                return CellState(IS_MINE_VALUE, cellType = CellType.IS_MINE)
            }
            val count = currentCell.countingAdjacentMines(mineCells)
            return CellState(count, cellType = CellType.NOT_MINE)
        }

        private const val IS_MINE_VALUE = -1
    }
}
