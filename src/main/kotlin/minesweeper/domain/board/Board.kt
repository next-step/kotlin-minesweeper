package minesweeper.domain.board

import minesweeper.domain.vo.Position
import minesweeper.domain.vo.PositionX
import minesweeper.domain.vo.PositionY
import minesweeper.domain.vo.PositiveNumber

class Board<T>(val values: List<List<T>>) : List<List<T>> by values {
    fun getHeight() = values.size

    fun getWidth() = values[0].size

    fun get(position: Position): T = values[position.y.value][position.x.value]

    fun <R> map(transform: (T) -> R): Board<R> {
        val result = values.map { it.map(transform) }
        return Board(result)
    }

    fun <R> mapPositioned(transform: (PositionX, PositionY, T) -> R): Board<R> {
        val result = values.mapIndexed { y, aValues ->
            aValues.mapIndexed { x, value ->
                transform(PositionX(x), PositionY(y), value)
            }
        }
        return Board(result)
    }

    companion object {
        fun <T> of(values: List<T>, width: PositiveNumber): Board<T> {
            return Board(values.chunked(width.value))
        }
    }
}
