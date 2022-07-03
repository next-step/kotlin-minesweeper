package view

import domain.Board
import domain.Cell
import domain.Cell.Companion.surroundMineCount
import domain.Location
import domain.Location.Companion.isMine

object OutputView {

    fun showBoard(board: Board) {
        println(MINESWEEPER_TITLE)
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
        return if (this.location.isMine(cells)) MINE_SYMBOL
        else surroundMineCount(this, cells).toString()
    }

    private const val MINESWEEPER_TITLE = "지뢰찾기 게임 시작"
    private const val MINE_SYMBOL = "*"
}
