package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Cells

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
        cells.forEach { cell ->
            print("${cell.shape()} ")
        }
    }

    private fun Cell.shape(): Char {
        return when {
            isMine -> '*'
            else -> 'C'
        }
    }
}
