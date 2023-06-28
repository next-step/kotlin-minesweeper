package minesweeper.ui

import minesweeper.domain.Board
import minesweeper.domain.ClearCell
import minesweeper.domain.MineCell

object BoardPrinter {
    const val MINE_CELL = "*"
    const val CLEAR_CELL = "C"
    const val BETWEEN_CELL = " "
    fun print(board: Board) {
        println("지뢰찾기 게임 시작")

        (0 until board.height).forEach { y ->
            (0 until board.width).forEach { x ->
                when (board.cell(x, y)) {
                    is MineCell -> print(MINE_CELL)
                    is ClearCell -> print(CLEAR_CELL)
                }
                print(BETWEEN_CELL)
            }
            println()
        }
    }
}
