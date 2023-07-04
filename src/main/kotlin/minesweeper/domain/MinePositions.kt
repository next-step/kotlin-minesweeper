package minesweeper.domain

import kotlin.random.Random

class MinePositions(rows: Rows, cols: Cols, mineCount: Int) {
    val minePositions: List<MinePosition>

    init {
        val rowsValue = rows.value
        val colsValue = cols.value

        val positions = mutableListOf<MinePosition>()
        repeat(mineCount) {
            var minePosition = MinePosition(Random.nextInt(rowsValue), Random.nextInt(colsValue))
            while (positions.contains(minePosition)) {
                minePosition = MinePosition(Random.nextInt(rowsValue), Random.nextInt(colsValue))
            }
            positions.add(minePosition)
        }
        minePositions = positions
    }
}
