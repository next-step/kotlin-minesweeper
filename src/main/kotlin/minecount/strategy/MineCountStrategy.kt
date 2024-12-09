package minecount.strategy

import map.Index
import map.Rows

interface MineCountStrategy {
    val points: Rows

    fun calculate(
        rowIndex: Index?,
        columnIndex: Index?,
    ): Int
}
