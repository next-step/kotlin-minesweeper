package minesweeper.view

import minesweeper.model.Board
import minesweeper.model.Cell
import minesweeper.model.Cells

class ResultView {
    fun printBoard(board: Board) {
        for (row in 0 until board.height) {
            printRow(board.getRowCells(row))
            println()
        }
    }

    fun printStartGame() {
        println("지뢰찾기 게임 시작")
    }

    fun printLoseGame() {
        println("Lose Game.")
    }

    fun printWinGame() {
        println("Win Game.")
    }

    private fun printRow(rowCells: Cells) {
        print(rowCells.cellList.joinToString("") { cell -> buildString { printCell(cell) } })
    }

    private fun printCell(cell: Cell) {
        if (cell.isMine) {
            print(CELL_SYMBOL)
            return
        }

        if (cell.isOpen) {
            print("${cell.mineAroundCount} ")
            return
        }

        print(CELL_SYMBOL)
    }

    companion object {
        private const val CELL_SYMBOL = "C "
        private const val MINE_SYMBOL = "* "
    }
}
