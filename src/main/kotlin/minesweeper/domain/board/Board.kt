package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Positions

@JvmInline
value class Board(val cells: Cells) {

    fun shuffleRandomMines(mineCount: Int) {
        val mineCells = Cells.makeMineCells(cells, mineCount)
        cells.change(mineCells)
    }

    companion object {
        fun of(boardSize: BoardSize): Board =
            Positions.of(boardSize).run {
                Board(Cells.of(this))
            }
    }
}
