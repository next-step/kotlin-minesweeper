package minesweeper.domain.board

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.Cell.Empty
import minesweeper.domain.cell.Cell.Mine
import minesweeper.domain.cell.CellMaker
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.Position

class MineBoard private constructor(val cells: Cells) {

    fun open(position: Position): BoardStatus {
        val cell = cells.open(position)
        if (cells.hasClosedEmptyCell()) {
            return getBoardStatusByCell(cell)
        }
        return BoardStatus.CLEAR
    }

    private fun getBoardStatusByCell(cell: Cell) =
        when (cell) {
            is Mine -> BoardStatus.BOOM
            is Empty -> BoardStatus.SAFE
        }

    companion object {
        fun of(
            width: Int,
            height: Int,
            numberOfMines: Int,
            cellMaker: CellMaker
        ): MineBoard {
            validate(width, height, numberOfMines)
            val cells = Cells.of(width, height, numberOfMines, cellMaker)
            return MineBoard(cells)
        }

        private fun validate(width: Int, height: Int, numberOfMines: Int) {
            val size = width * height
            require(width >= 0 && height >= 0) { "property must be zero or positive." }
            require(numberOfMines in (0..size)) { "number of mines must be within range of 0 ~ $size" }
        }
    }
}

fun mineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
