package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

@JvmInline
value class Board(val cells: Cells) {

    fun open(position: Position) {
        cells.open(position)
    }

    fun isOpenedMine(): Boolean {
        return cells.isOpenedMine()
    }

    fun isAllOpenedExcludeMine(): Boolean {
        return cells.isAllOpenedExcludeMine()
    }

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
