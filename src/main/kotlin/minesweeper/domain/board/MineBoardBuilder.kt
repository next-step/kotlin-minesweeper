package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Position

fun mineBoard(
    minePicker: PositionPicker,
    block: MineBoardBuilder.() -> Unit
): MineBoard = MineBoardBuilder(minePicker).apply(block).build()

class MineBoardBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var size: MineBoardSize
    private lateinit var mineCount: MineTotal

    fun size(size: MineBoardSize) {
        this.size = size
    }

    operator fun Width.times(height: Height): MineBoardSize = MineBoardSize(height, this)

    fun mineCount(count: MineTotal) {
        mineCount = count
    }

    fun build(): MineBoard = MineBoard(cells())

    private fun cells(): Map<Position, Cell> =
        cells {
            positions(positions())
        }

    private fun positions(): Positions =
        positions(minePicker) {
            allPositions(size.allPositionsOfRowAndColumns)
            mineTotal(mineCount)
        }
}
