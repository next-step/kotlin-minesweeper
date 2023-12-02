package minesweeper.domain

import java.util.*

class Row(val cells: SortedSet<Cell>) : Comparable<Row> {
    override fun compareTo(other: Row): Int {
        if (this.cells.isEmpty() || other.cells.isEmpty()) {
            return 0
        }
        return this.cells.first().point.y.compareTo(other.cells.first().point.y)
    }
}
