package minesweeper.views

import minesweeper.domain.Board
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None

object OutputView {

    fun printBoard(board: Board) {
        println(START_PRINT_BOARD)
        val sb = StringBuilder()
        var block = ""
        for (i in 0 until board.blocks.size) {
            setWidth(i, board, sb)
            block = drawNone(board, i, block)
            block = drawMine(board, i, block)
            sb.append(block)
        }
        println(sb)
    }

    private fun setWidth(i: Int, board: Board, sb: StringBuilder) {
        if (i != 0 && i % board.area.width.value == 0) {
            sb.append("\n")
        }
    }

    private fun drawNone(board: Board, i: Int, block: String): String {
        var block1 = block
        if (board.blocks[i] is None) {
            block1 = "C"
        }
        return block1
    }

    private fun drawMine(board: Board, i: Int, block: String): String {
        var block1 = block
        if (board.blocks[i] is Mine) {
            block1 = "*"
        }
        return block1
    }

    const val START_PRINT_BOARD = "지뢰찾기 게임 시작"
}
