package minesweeper.domain

class Minesweeper(
    val positions: Positions,
    minePositions: MinePositions
) {
    init {
        minePositions.minePositions.forEach {
            positions.updatePositionValue(it.rowPosition, it.colPosition, -1)
        }

        val mineCount = MineCount(positions)
        mineCount.initMineCount()
    }

    fun open(position: String) {
        val (row, col) = splitStringPosition(position)
        positions.open(row, col)
    }

    fun position(rows: Int, cols: Int): Position {
        return positions.position(rows, cols)
    }

    fun isMinePosition(position: String): Boolean {
        val (row, col) = splitStringPosition(position)
        return positions.isMinePosition(row, col)
    }

    fun isGameWin(mineCount: Int): Boolean {
        return positions.notOpenPositionCount() == mineCount
    }

    private fun splitStringPosition(position: String) = position.split(POSITION_SPLIT_SYMBOL).map { it.trim().toInt() }

    companion object {
        const val POSITION_SPLIT_SYMBOL = ","
        fun from(rows: Rows, cols: Cols, mine: MineValue): Minesweeper {
            val rowsValue = rows.value
            val colsValue = cols.value
            val positionArray = Array(rowsValue) {
                Array(colsValue) {
                    Position()
                }
            }

            return Minesweeper(Positions(positionArray), MinePositions.from(rows, cols, mine))
        }
    }
}
