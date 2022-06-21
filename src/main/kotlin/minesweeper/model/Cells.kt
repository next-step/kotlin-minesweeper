package minesweeper.model

import kotlin.reflect.KProperty

class Cells(
    val cells: List<Cell>
) {
    val size: Int by SizeDelegator()

    val mineCount
        get() = cells.count { it.isMine() }
}

class SizeDelegator {
    operator fun getValue(thisRef: Cells, prop: KProperty<*>): Int {
        return thisRef.cells.size
    }
}
