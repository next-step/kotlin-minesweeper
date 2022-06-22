package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard

object ConsoleResultView : ResultView {
    override fun printMineBoard(board: MineBoard) {
        println("\n지뢰찾기 게임 시작")

        val boardView = board.board
            .joinToString(ROW_SEPARATOR) { generateBoardRowView(it.cells) }

        println(boardView)
    }

    private fun generateBoardRowView(row: List<Cell>): String {
        val cellMarks = row.map { findCellMark(it) }
        return cellMarks.joinToString(CELL_SEPARATOR)
    }

    private fun findCellMark(cell: Cell): String {
        if (cell.isMine()) {
            return MINE_MARK
        }
        return NON_MINE_MARK
    }

    private const val MINE_MARK = "*"
    private const val NON_MINE_MARK = "C"
    private const val CELL_SEPARATOR = " "
    private const val ROW_SEPARATOR = "\n"
}
