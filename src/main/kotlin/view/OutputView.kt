package view

import map.Board
import map.Cell
import map.Mine
import map.None

object OutputView {

    fun drawBoard(board: Board) {
        board.board.forEach {
            drawLine(it)
            println("")
        }
    }

    private fun drawLine(line: MutableList<Cell>) {
        line.forEach {
            drawCell(it)
            print(" ")
        }
    }

    private fun drawCell(cell: Cell) {
        when (cell) {
            is None -> print("C")
            is Mine -> print("*")
        }
    }
}
