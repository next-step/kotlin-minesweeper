package minesweeper.domain

import kotlin.random.Random

class MinePositions(val minePositions: List<MinePosition>) {
    val mineCount: Int = minePositions.size

    companion object {
        fun from(rows: Rows, cols: Cols, mine: MineValue): MinePositions {
            val rowsValue = rows.value
            val colsValue = cols.value

            val positions = mutableListOf<MinePosition>()
            repeat(mine.value) {
                var minePosition = MinePosition(Random.nextInt(rowsValue), Random.nextInt(colsValue))
                while (positions.contains(minePosition)) {
                    minePosition = MinePosition(Random.nextInt(rowsValue), Random.nextInt(colsValue))
                }
                positions.add(minePosition)
            }
            return MinePositions(positions)
        }
    }
}
