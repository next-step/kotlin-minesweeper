package minesweeper.domain

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.board.Height
import minesweeper.domain.board.Positions
import minesweeper.domain.board.Width
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position

fun board(
    minePicker: PositionPicker,
    block: BoardBuilder.() -> Unit
): Board = BoardBuilder(minePicker).apply(block).build()

class BoardBuilder(
    private val minePicker: PositionPicker,
) {
    private lateinit var size: BoardSize
    private lateinit var mineCount: MineCount
    private lateinit var positions: Positions

    fun size(height: Height, width: Width) {
        size = BoardSize(height, width)
    }

    fun mineCount(count: MineCount) {
        mineCount = count
    }

    fun build(): Board {
        positions = positions(minePicker) {
            allPositions(size.allPositionsOfRowAndColumns)
            mineCount(mineCount)
        }
        return Board(createCells())
    }

    private fun createCells(): Set<Cell> =
        positions.allPositions.map { Cell(it, it.determineMark()) }.toSet()

    private fun Position.determineMark(): CellMark =
        if (positions.isMine(this)) CellMark.MINE
        else CellMark.EMPTY
}
