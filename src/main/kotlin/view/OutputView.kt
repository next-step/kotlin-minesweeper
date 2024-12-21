package view

import domain.Cell
import domain.CellStatus
import domain.GameResult
import domain.MineBoard

object OutputView {
    fun showMineSweeperBoard(board: MineBoard) {
        val cells = board.cells
        val cellsByRow = cells.cells.groupBy { it.coordinate.row.value }

        cellsByRow.toSortedMap().forEach { (_, rowCells) ->
            val sortedRow = rowCells.sortedBy { it.coordinate.col.value }
            sortedRow.forEach { cell ->
                printCell(cell, board)
            }
            println()
        }
    }

    private fun printCell(
        cell: Cell,
        board: MineBoard,
    ) {
        if (cell.status == CellStatus.CLOSED) {
            print(CLOSED_CELL)
            return
        }

        when (cell) {
            is Cell.MineCell -> print(MINE_CELL)
            is Cell.EmptyCell -> print("${board.countAdjacentMines(cell)} ")
        }
    }

    fun showGameResult(result: GameResult) {
        when (result) {
            GameResult.SUCCESS -> print(SUCCESS_MESSAGE)
            GameResult.FAILURE -> print(FAIL_MESSAGE)
        }
    }

    private const val MINE_CELL = "* "
    private const val CLOSED_CELL = "C "
    private const val SUCCESS_MESSAGE = "Success Game"
    private const val FAIL_MESSAGE = "Lose Game."
}
