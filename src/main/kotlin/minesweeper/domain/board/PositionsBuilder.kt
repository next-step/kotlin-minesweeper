package minesweeper.domain.board

import minesweeper.domain.cell.Position

fun positions(
    minePicker: PositionPicker,
    block: PositionsBuilder.() -> Unit
): Positions = PositionsBuilder(minePicker).apply(block).build()

class PositionsBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var positions: Positions

    fun allPositions(positions: Set<Position>) {
        this.positions = Positions(positions)
    }

    fun mineCount(count: MineCount) {
        val minePositions = minePicker.pick(positions.value, count.value)
        positions.pickMines(minePositions)
    }

    fun build(): Positions {
        check(positions.isMinePicked) { "지뢰가 선정되지 않았습니다" }
        return positions
    }
}
