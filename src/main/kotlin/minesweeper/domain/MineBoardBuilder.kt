package minesweeper.domain

import minesweeper.domain.board.Board
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position

fun mineBoard(
    minePicker: PositionPicker,
    block: MineBoardBuilder.() -> Unit
): Board = MineBoardBuilder(minePicker).apply(block).build()

class MineBoardBuilder(
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

    fun build(): Board =
        Board(createCells(minePositions))

    private fun createCells(minePositions: Set<Position>): Set<Cell> =
        allPositions.map { Cell(it, it.determineMark(minePositions)) }.toSet()

    private fun Position.determineMark(minePositions: Set<Position>): CellMark =
        if (this in minePositions) CellMark.MINE
        else CellMark.EMPTY
}
