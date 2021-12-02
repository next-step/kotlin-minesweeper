package minesweeper.domain

class Positions(val positions: List<Position>) {

    fun mapToIsIn(other: Positions): Map<Position, Boolean> {
        return positions.associateWith { it in other }
    }

    operator fun contains(position: Position) = position in positions

    operator fun minus(position: Position) = Positions(positions - position)

    companion object {

        fun from(rowRange: IntRange, columnRange: IntRange): Positions {
            val positions = rowRange.flatMap { row ->
                columnRange.mapNotNull { column ->
                    findPosition(row, column)
                }
            }
            return Positions(positions)
        }

        fun from(height: Height, width: Width): Positions {
            return from(
                rowRange = Row.START_VALUE..height.value,
                columnRange = Column.START_VALUE..width.value,
            )
        }

        private fun findPosition(row: Int, column: Int): Position? {
            return try {
                Position.from(row = row, column = column)
            } catch (_: Exception) {
                null
            }
        }
    }
}

fun Positions.slice(indexes: List<Int>): Positions {
    return try {
        sliceBy(indexes)
    } catch (_: ArrayIndexOutOfBoundsException) {
        throw IllegalArgumentException("${indexes}는 [0, ${positions.size}) 범위 밖의 값을 포함하고 있습니다.")
    }
}

fun Positions.count(predicate: (Position) -> Boolean): Int {
    return positions.count(predicate)
}

private fun Positions.sliceBy(indexes: List<Int>): Positions {
    return Positions(positions.slice(indexes))
}
