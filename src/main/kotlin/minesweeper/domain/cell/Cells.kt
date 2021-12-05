package minesweeper.domain.cell

import minesweeper.domain.board.BoardSettings
import minesweeper.domain.position.Position

@JvmInline
value class Cells(private val _values: Map<Position, Cell>) {

    val values: Map<Position, Cell>
        get() = this._values.toSortedMap()

    companion object {
        fun of(settings: BoardSettings, minePositions: List<Position>): Cells {
            val values = mapOf<Position, Cell>().toMutableMap()
            repeat(settings.width) { row ->
                repeat(settings.height) { column ->
                    val position = Position(row, column)
                    values[position] = if (position in minePositions) Mine else Block
                }
            }
            return Cells(values)
        }
    }
}
