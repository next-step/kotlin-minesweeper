package minesweeper.domain

import java.util.*

class Row(val cells: SortedSet<Cell>) : Comparable<Row> {
    init {
        val firstY = cells.firstOrNull()?.point?.y
        require (cells.all { it.point.y == firstY }) {
            throw IllegalArgumentException("한 줄을 구성하는 Cell은 모두 같은 y좌표를 가져야합니다.")
        }
    }

    override fun compareTo(other: Row): Int {
        if (this.cells.isEmpty() || other.cells.isEmpty()) {
            return 0
        }
        return this.cells.first().point.y.compareTo(other.cells.first().point.y)
    }
}
