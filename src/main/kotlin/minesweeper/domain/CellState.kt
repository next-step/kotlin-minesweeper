package minesweeper.domain

class CellState(
    val mineCount: Int = 0,
    val cellType: CellType
) {
    companion object {
        fun of(position: Position, minePositions: Positions, cellType: CellType): CellState {
            val mineCount =
                if (cellType == CellType.NON_MINE) getNearMineCount(position.nearCellPositions, minePositions) else 0

            return CellState(mineCount, cellType)
        }

        private fun getNearMineCount(nearPositions: Positions, minePositions: Positions): Int {
            return nearPositions.count { it in minePositions }
        }
    }
}
