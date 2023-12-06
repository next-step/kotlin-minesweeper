package minesweeper.domain

import minesweeper.domain.board.Positions
import minesweeper.domain.cell.Position

fun positions(
    minePicker: PositionPicker,
    block: PositionsBuilder.() -> Unit
): Positions = PositionsBuilder(minePicker).apply(block).build()

class PositionsBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var allPositions: Set<Position>
    private lateinit var minePositions: Set<Position>

    fun allPositions(positions: Set<Position>) {
        allPositions = positions
    }

    fun mineCount(count: MineCount) {
        minePositions = minePicker.pick(allPositions, count.value)
    }

    fun build(): Positions = Positions(allPositions, minePositions)
}
