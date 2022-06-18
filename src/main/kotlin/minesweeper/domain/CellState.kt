package minesweeper.domain

class CellState(
    val mineCount: Int = 0,
    val cellType: CellType
) {
    companion object {
        fun of(position: Position, minePositions: Positions): CellState {
            val cellType = minePositions.cellType(position)
            val mineCount =
                if (cellType == CellType.NON_MINE) position.getNearMineCount(minePositions) else 0

            return CellState(mineCount, cellType)
        }

        private fun Positions.cellType(position: Position): CellType {
            return if (contains(position)) CellType.MINE else CellType.NON_MINE
        }
    }
}
