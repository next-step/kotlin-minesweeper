package minesweeper.domain

class Minesweeper(
    val positions: Positions,
    minePositions: MinePositions
) {
    init {
        for (minePosition in minePositions.minePositions) {
            positions.updatePositionValue(minePosition.rowPosition, minePosition.colPosition, -1)
        }

        val mineCount = MineCount(positions)
        mineCount.initMineCount()
    }

    companion object {
        fun from(rows: Rows, cols: Cols, mine: MineValue): Minesweeper {
            val rowsValue = rows.value
            val colsValue = cols.value
            val positions = Array(rowsValue) {
                arrayOfNulls<Position>(
                    colsValue
                )
            }
            return Minesweeper(Positions(positions), MinePositions.from(rows, cols, mine))
        }
    }
}
