package minesweeper.views

import minesweeper.domain.Board
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.game.GameResult
import minesweeper.domain.game.State

object OutputView {
    fun printStart() {
        println(START_PRINT_BOARD)
    }

    fun printBoard(result: GameResult) {
        val state = result.state
        if (state == State.LOSE) {
            println(LOSE_MESSAGE)
            return
        }
        if (state == State.WIN) {
            println(WIN_MESSAGE)
            return
        }
        val board = result.board
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
            block1 = board.blocks[i].getMineNearCount()?.toString() ?: NONE
        }
        return block1
    }

    private fun drawMine(board: Board, i: Int, block: String): String {
        var block1 = block
        if (board.blocks[i] is Mine) {
            block1 = NONE
        }
        return block1
    }

    private const val START_PRINT_BOARD = "지뢰찾기 게임 시작"
    private const val NONE = "C"
    private const val LOSE_MESSAGE = "Lose Game."
    private const val WIN_MESSAGE = "Win Game."
}
