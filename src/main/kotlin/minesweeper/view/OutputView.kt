package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Position

object OutputView {

    private const val START_MINE_SWEEPER_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE_CELL_DISPLAY_NAME = "*"
    private const val SAFETY_CELL_DISPLAY_NAME = "C"
    private const val LINE_SEPARATOR = "\n"

    fun printMineSweeper(board: Board) {
        println(START_MINE_SWEEPER_MESSAGE)
        printBoard(board)
    }

    private fun printBoard(board: Board) {
        board.cellList.forEach { (position, cell) ->
            print(getCellStringByPosition(position, cell))
        }
    }

    private fun getCellStringByPosition(position: Position, cell: Cell): String {
        if (position.isRowStart()) {
            return LINE_SEPARATOR + cell.toDisplayName() + " "
        }
        return cell.toDisplayName() + " "
    }

    private fun Cell.toDisplayName(): String = when (this) {
        Cell.MineCell -> MINE_CELL_DISPLAY_NAME
        Cell.SafetyCell -> SAFETY_CELL_DISPLAY_NAME
    }
}
