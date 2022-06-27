package view

import domain.Board
import domain.Cell

object OutputView {

    fun showBoard(board: Board) {
        println(MINESWEEPER_TITLE)
        val cells = board.cells
        val width = board.dimension.width

        cells.forEach { location, cell ->
            if (location.column.value % width == 0) println()
            print(cell.display())
        }
    }

    private fun Cell.display(): String {
        return when (this) {
            is Cell.Mine -> MINE_SYMBOL
            is Cell.Ground -> GROUND_SYMBOL
        }
    }

    private const val MINESWEEPER_TITLE = "지뢰찾기 게임 시작"
    private const val MINE_SYMBOL = "*"
    private const val GROUND_SYMBOL = "C"
}
