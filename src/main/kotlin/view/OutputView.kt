package view

import map.Board
import map.Cell
import map.Mine
import map.None

object OutputView : OutputViewInterface {

    private const val IMG_MINE = "*"
    private const val IMG_NONE = "C"
    private const val TEXT_GAME_CLEAR = "Clear Game."
    private const val TEXT_GAME_OVER = "Lose Game."

    override fun drawBoard(board: Board) {
        board.mineBoard.forEach {
            drawLine(it)
            println("")
        }
    }

    override fun printGameClear() {
        println(TEXT_GAME_CLEAR)
    }

    override fun printGameOver() {
        println(TEXT_GAME_OVER)
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
