package minesweeper.domain

import minesweeper.domain.board.BoardSize
import minesweeper.domain.board.Height
import minesweeper.domain.board.MineBoard
import minesweeper.domain.board.Positions
import minesweeper.domain.board.Width

fun mineBoard(
    minePicker: PositionPicker,
    block: MineBoardBuilder.() -> Unit
): MineBoard = MineBoardBuilder(minePicker).apply(block).build()

class MineBoardBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var size: BoardSize
    private lateinit var mineCount: MineCount

    fun size(height: Height, width: Width) {
        size = BoardSize(height, width)
    }

    fun mineCount(count: MineCount) {
        mineCount = count
    }

    private fun positions(): Positions =
        positions(minePicker) {
            allPositions(size.allPositionsOfRowAndColumns)
            mineCount(mineCount)
        }

    fun build(): MineBoard {
        return MineBoard.from(positions())
    }
}
