package minesweeper.domain

abstract class CellState(
    val cellType: CellType,
    var isOpen: Boolean = false
) {
    abstract fun isBomb(): Boolean

    abstract fun isNonMine(): Boolean

    abstract fun getNearMineCount(): Int

    fun click() {
        isOpen = true
    }

    companion object {
        fun of(position: Position, minePositions: Positions): CellState {
            val cellType = minePositions.cellType(position)
            return if (cellType == CellType.NON_MINE) NonMineCellState(position.getNearMineCount(minePositions)) else MineCellState()
        }

        private fun Positions.cellType(position: Position): CellType {
            return if (contains(position)) CellType.MINE else CellType.NON_MINE
        }
    }
}
