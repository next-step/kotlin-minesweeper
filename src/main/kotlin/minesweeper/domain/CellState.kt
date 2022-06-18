package minesweeper.domain

class CellState(
    val mineCount: Int = 0,
    val cellType: CellType
) {
    companion object {
        fun of(position: Position, minePositions: Positions, cellType: CellType): CellState {
            val mineCount =
                if (cellType == CellType.NON_MINE) position.getNearMineCount(minePositions) else 0

            return CellState(mineCount, cellType)
        }
    }
}
