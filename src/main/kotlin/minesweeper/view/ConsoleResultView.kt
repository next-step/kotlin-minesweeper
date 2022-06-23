package minesweeper.view

import minesweeper.model.Cell
import minesweeper.model.MineBoard

object ConsoleResultView : ResultView {
    override fun printMineBoard(board: MineBoard) {
        println("\n지뢰찾기 게임 시작")

        val boardView = board.board
            .joinToString(ROW_SEPARATOR) { generateBoardRowView(it.cells, board) }

        println(boardView)
    }

    private fun generateBoardRowView(row: List<Cell>, board: MineBoard): String {
        val cellMarks = row.map { findCellMark(it, board) }
        return cellMarks.joinToString(CELL_SEPARATOR)
    }

    private fun findCellMark(cell: Cell, board: MineBoard): String {
        if (cell.isMine()) {
            return MINE_MARK
        }
        return cell.findSurroundingMineCountSum(board).toString()
    }

    private const val MINE_MARK = "*"
    private const val CELL_SEPARATOR = " "
    private const val ROW_SEPARATOR = "\n"
}
