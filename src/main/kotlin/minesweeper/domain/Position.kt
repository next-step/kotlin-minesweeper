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

    fun aroundPositionList(): List<Position> {
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
            .filterNot { it.row == row && it.column == column }
    }

    companion object {

        private const val START_COORDINATE = 0
        private const val MINUS_COORDINATE = -1
        private const val PLUS_COORDINATE = 1
        private const val DELIMITER = ","
        private const val POSITION_MINUS_VALUE = 1

        private const val INPUT_ILLEGAL_ARGUMENTS_EXCEPTION = "입력값의 Size는 2개여야 합니다. 현재 갯수 == %s"
        private val AROUND_COORDINATES = listOf(MINUS_COORDINATE, START_COORDINATE, PLUS_COORDINATE)

        const val INPUT_NUMBER_FORMAT_EXCEPTION = "입력값은 Int여야 합니다."

        fun from(input: String): Position {
            val coordinates: List<Int> = input
                .split(DELIMITER)
                .map {
                    it.trim().toIntOrNull() ?: throw IllegalArgumentException(INPUT_NUMBER_FORMAT_EXCEPTION)
                }
            require(coordinates.size == 2) { INPUT_ILLEGAL_ARGUMENTS_EXCEPTION.format(coordinates.size) }
            return of(Row.from(coordinates[0] - POSITION_MINUS_VALUE), Column.from(coordinates[1] - POSITION_MINUS_VALUE))
        }

        fun of(row: Row, column: Column): Position {
            return Position(row, column)
        }
    }
}
