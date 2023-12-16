package minesweeper.domain.board

import minesweeper.domain.cell.Cell

fun mineBoard(
    minePicker: PositionPicker,
    block: MineBoardBuilder.() -> Unit
): MineBoard = MineBoardBuilder(minePicker).apply(block).build()

class MineBoardBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var size: MineBoardSize
    private lateinit var mineCount: MineCount

    fun size(height: Height, width: Width) {
        size = MineBoardSize(height, width)
    }

    fun mineCount(count: MineCount) {
        mineCount = count
    }

    fun build(): MineBoard = MineBoard(cells())

    private fun cells(): Set<Cell> =
        cells {
            positions(positions())
        }

    private fun positions(): Positions =
        positions(minePicker) {
            allPositions(size.allPositionsOfRowAndColumns)
            mineCount(mineCount)
        }
}
