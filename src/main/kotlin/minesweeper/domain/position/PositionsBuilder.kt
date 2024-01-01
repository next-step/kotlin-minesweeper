package minesweeper.domain.position

import minesweeper.domain.board.MineTotal

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

    fun mineTotal(total: MineTotal) {
        val minePositions = minePicker.pick(positions.value, total.value)
        positions.pickMines(minePositions)
    }

    fun build(): Positions {
        check(positions.isMinePicked) { "지뢰가 선정되지 않았습니다" }
        return positions
    }
}
