package tdd.minesweeper.provider

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Rows

fun interface RowsProvider {
    fun provide(area: Area, mineCapacity: Int): Rows
}
