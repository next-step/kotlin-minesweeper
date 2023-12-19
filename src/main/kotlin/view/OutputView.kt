package view

import map.Board
import map.Cell
import map.Mine
import map.None

object OutputView: OutputViewInterface {

    private const val IMG_MINE = "*"
    private const val IMG_NONE = "C"

    override fun drawBoard(board: Board) {
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
        if (isCloseCell(cell)) return

        printCell(cell)
    }

    private fun printCell(cell: Cell) {
        when (cell) {
            is None -> {
                print(cell.mineCnt)
            }

            is Mine -> {
                print(IMG_MINE)
            }
        }
    }

    private fun isCloseCell(cell: Cell): Boolean {
        if (!cell.isOpen()) {
            print(IMG_NONE)
            return true
        }
        return false
    }
}
