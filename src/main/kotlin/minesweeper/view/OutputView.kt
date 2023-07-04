package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Mine
import minesweeper.domain.Normal

object OutputView {
    fun printGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        board.cells.forEach { row ->
            printCells(row)
            println()
        }
    }

    private fun printCells(cells: Cells) {
        cells.values.forEach { cell ->
            print("${cell.shape()} ")
        }
    }

    private fun Cell.shape(): Char {
        return when (this) {
            is Mine -> '*'
            is Normal -> 'C'
        }
    }
}
