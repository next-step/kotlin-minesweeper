package domain

import java.util.*

class Map(private val rows: SortedSet<Row>) : SortedSet<Row> by rows {
    init {
        require(rows.all { it.size == rows.first().size }) { "맵에 포함된 행들의 크기는 모두 동일해야 합니다." }
    }

    constructor(vararg rows: Row) : this(rows.toSortedSet())
}
