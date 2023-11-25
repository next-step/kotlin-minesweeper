package minesweeper.view

import minesweeper.model.map.MineMap
import minesweeper.model.point.Point

object OutputView {
    fun mineMap(mineMap: MineMap) {
        mineMap.value.forEach { this.printRow(it) }
    }

    private fun printRow(row: List<Point>) {
        row.joinToString(separator = " ", postfix = "\n") { it.symbol() }
    }
}
