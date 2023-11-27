package domain

import java.util.SortedSet

class Row(val columns: SortedSet<Coordinate>) : SortedSet<Coordinate> by columns,
    Comparable<Row> {
    init {
        require(columns.size >= MIN_SIZE) { "행에는 최소 ${MIN_SIZE}개 이상의 좌표가 포함되어야 합니다." }
        require(columns.first().x == START_COLUMN) { "첫번째 좌표의 x값은 ${START_COLUMN}이어야 합니다." }
        require(columns.all { it.y == columns.first().y }) { "좌표들의 y 값은 모두 동일해야 합니다." }
        require(checkColumDiffIsOne()) { "좌표들의 x 값은 1씩 커져야합니다" }
    }

    constructor(vararg coordinates: Coordinate) : this(coordinates.toSortedSet())

    fun getY(): Int {
        return first().y
    }

    fun findAndSetMine(mines: Coordinates): Row {
        return Row(columns.map {
            if (it in mines) {
                Mine(it.x, it.y)
            } else SafeZone(it.x, it.y)
        }.toSortedSet())
    }

    private fun checkColumDiffIsOne(): Boolean {
        return columns.map { it.x } == (columns.first().x..columns.last().x).toList()
    }

    override fun compareTo(other: Row): Int {
        return first().y - other.first().y
    }

    companion object {
        private const val MIN_SIZE = 1
        private const val START_COLUMN = 0
    }
}
