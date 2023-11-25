package domain

import java.util.*

class Map(private val rows: SortedSet<Row>) : SortedSet<Row> by rows {
    init {
        require(rows.all { it.size == first().size }) { "맵에 포함된 행들의 크기는 모두 동일해야 합니다." }
        require(rows.all { first().getY() == START_ROW }) { "맵의 행들의 y값은 ${START_ROW}부터 시작해야 합니다." }
    }

    constructor(vararg rows: Row) : this(rows.toSortedSet())

    companion object {
        private const val MIN_SIZE = 1
        private const val START_ROW = 0
    }
}
