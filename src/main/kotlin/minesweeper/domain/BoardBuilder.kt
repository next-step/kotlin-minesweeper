package minesweeper.domain

import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardSize
import minesweeper.domain.board.Height
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
    private val allPositions by lazy { size.allPositionsOfRowAndColumns }
    private lateinit var minePositions: Set<Position>

    fun size(height: Height, width: Width) {
        size = BoardSize(height, width)
    }

    fun mineCount(count: MineCount) {
        minePositions = minePicker.pick(allPositions, count.value)
    }

    fun build(): Board =
        Board(createCells(minePositions))

    private fun createCells(minePositions: Set<Position>): Set<Cell> =
        allPositions.map { Cell(it, it.determineMark(minePositions)) }.toSet()

    private fun Position.determineMark(minePositions: Set<Position>): CellMark =
        if (this in minePositions) CellMark.MINE
        else CellMark.EMPTY
}
