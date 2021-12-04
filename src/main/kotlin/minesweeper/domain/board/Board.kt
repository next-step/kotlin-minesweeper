package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.CellsState
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class Board(val cells: Cells) {

    fun open(position: Position): CellsState = cells.open(position)

    companion object {
        fun of(boardSize: BoardSize, mineCount: Int): Board =
            Positions.of(boardSize).run {
                val cells = Cells.of(this).apply {
                    val mineCells = Cells.makeMineCells(this, mineCount)
                    this.inputMineCells(mineCells)
                }
                Board(cells)
            }
    }
}
