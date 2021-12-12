package minesweeper.domain.position

import minesweeper.domain.board.Height
import minesweeper.domain.board.Width

class Positions(val positions: List<Position>) {

    fun mapToIsIn(other: Positions): Map<Position, Boolean> {
        return positions.associateWith { it in other }
    }

    operator fun contains(position: Position) = position in positions

    companion object {

        fun from(height: Height, width: Width): Positions {
            return from(
                rowRange = Row.START_VALUE..height.value,
                columnRange = Column.START_VALUE..width.value,
            )
        }

        fun from(rowRange: IntRange, columnRange: IntRange): Positions {
            val positions = rowRange.flatMap { row ->
                columnRange.map { column ->
                    Position.from(row, column)
                }
            }
            return Positions(positions)
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
