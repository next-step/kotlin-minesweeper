package view

import map.Board
import map.Cell
import map.Mine
import map.None

object OutputView {

    private const val IMG_MINE = "*"
    private const val IMG_NONE = "C"

    fun drawBoard(board: Board) {
        board.mineBoard.forEach {
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
            is None -> print(cell.mineCnt)
            is Mine -> print(IMG_MINE)
        }
    }
}
