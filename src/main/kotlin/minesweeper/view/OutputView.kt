package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell

object OutputView {

    private const val START_MINE_SWEEPER_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE_CELL_DISPLAY_NAME = "*"

    fun printMineSweeper(board: Board) {
        println(START_MINE_SWEEPER_MESSAGE)
        printBoard(board)
    }

    private fun printBoard(board: Board) {
        board.cellList.forEach { cell ->
            print(getCellStringByPosition(cell))
        }
    }

    private fun getCellStringByPosition(cell: Cell): String {
        if (cell.isRowStartCell()) {
            return System.lineSeparator() + cell.toDisplayName() + " "
        }
        return cell.toDisplayName() + " "
    }

    private fun Cell.toDisplayName(): String = when (this) {
        is Cell.MineCell -> MINE_CELL_DISPLAY_NAME
        is Cell.SafetyCell -> "$mineCount"
    }
}
