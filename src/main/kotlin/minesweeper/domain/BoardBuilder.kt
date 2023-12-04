package minesweeper.domain

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.board.Height
import minesweeper.domain.board.Width

fun board(
    minePicker: PositionPicker,
    block: BoardBuilder.() -> Unit
): Board = BoardBuilder(minePicker).apply(block).build()

class BoardBuilder(
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

    fun build(): Board =
        mineBoard(minePicker) {
            allPositions(size.allPositionsOfRowAndColumns)
            mineCount(mineCount)
        }
}
