package view

import domain.Board
import domain.Cell
import domain.Cell.Companion.surroundMineCount
import domain.Location
import domain.Location.Companion.isMine
import java.lang.IllegalArgumentException

object OutputView {

    fun showBoard(board: Board) {
        val cells = board.cells
        val width = board.dimension.width

        cells.forEach { location, cell ->
            if (location.column.value % width == 0) println()
            print(cell.display(cells))
        }
        println()
        println()
    }

    private fun Cell.display(cells: Map<Location, Cell>): String {
        return if(this.location.closed) HIDE_SYMBOL
        else if (isMine(this.location, cells)) MINE_SYMBOL
        else surroundMineCount(this, cells).toString()
    }

    fun displayGameStart() {
        println(MINESWEEPER_TITLE)
    }

    fun displayGameEnd() {
        println(LOSE_GAME)
    }

    private const val MINESWEEPER_TITLE = "지뢰찾기 게임 시작"
    private const val MINE_SYMBOL = "*"
    private const val HIDE_SYMBOL = "C"
    private const val LOSE_GAME = "Lose Game."
}
