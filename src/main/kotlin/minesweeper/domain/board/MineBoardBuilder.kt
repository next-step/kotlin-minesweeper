package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.cells
import minesweeper.domain.position.Position
import minesweeper.domain.position.PositionPicker
import minesweeper.domain.position.Positions
import minesweeper.domain.position.positions

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

    fun build(): MineBoard = MineBoard(createCells())

    private fun createCells(): Map<Position, Cell> =
        cells {
            positions(createPositions())
        }

    private fun createPositions(): Positions =
        positions(minePicker) {
            allPositions(size.allPositions)
            mineTotal(mineCount)
        }
}
