package minesweeper.domain

data class Position(val row: Row, val column: Column) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return compareValuesBy(
            this,
            other,
            { it.column },
            { it.row }
        )
    }

    fun isRowStart(): Boolean = row.isStart

    fun countAroundPositionsContainOthers(others: List<Position>): Int {
        return aroundPositionList().filter {
            others.contains(it)
        }.count()
    }

    private fun aroundPositionList(): List<Position> {
        val aroundXList = AROUND_COORDINATES
            .map { coordinate ->
                coordinate + row.value
            }
            .filter { it >= 0 }

        val aroundYList = AROUND_COORDINATES
            .map { coordinate ->
                coordinate + column.value
            }
            .filter { it >= START_COORDINATE }

        return aroundXList
            .flatMap { x ->
                aroundYList.map { y ->
                    Position(Row.from(x), Column.from(y))
                }
            }
    }

    companion object {

        private const val START_COORDINATE = 0
        private const val MINUS_COORDINATE = -1
        private const val PLUS_COORDINATE = 1

        private val AROUND_COORDINATES = listOf(MINUS_COORDINATE, START_COORDINATE, PLUS_COORDINATE)

        fun from(row: Row, column: Column): Position {
            return Position(row, column)
        }
    }
}
