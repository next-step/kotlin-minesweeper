package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

@JvmInline
value class Board(val cells: Cells) {

    fun shuffleRandomMines(mineCount: Int) {
        val mineCells = Cells.makeMineCells(cells, mineCount)
        cells.change(mineCells)
    }

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
        fun of(boardSize: BoardSize): Board =
            Positions.of(boardSize).run {
                Board(Cells.of(this))
            }
    }
}
