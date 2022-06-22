package ui

import domain.Board
import domain.Cell

object OutputUI {

    private const val MOD_CONDITION = 0

    fun showBoard(board: Board) {
        println("지뢰찾기 게임 시작")
        val cells = board.cells
        val width = board.dimension.width

        cells.forEachIndexed { i, cell ->
            if (i % width == MOD_CONDITION) println()
            print(cell.display())
        }
    }

    private fun Cell.display(): String {
        return when (this) {
            is Cell.Mine -> "*"
            is Cell.Safe -> "C"
        }
    }
}
