package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.state.Finished
import minesweeper.domain.state.Lose
import minesweeper.domain.state.State
import minesweeper.domain.state.Win

object OutputView {

    private const val START_MINE_SWEEPER_MESSAGE = "지뢰찾기 게임 시작"
    private const val OPEN_MINE_CELL = "*"
    private const val NOT_OPEN_CELL_DISPLAY_NAME = "C"
    private const val WIN_GAME = "Win Game."
    private const val LOSE_GAME = "Lose Game."

    fun printMineSweeper(board: Board) {
        printBoard(board)
    }

    fun printStartGame() {
        println(START_MINE_SWEEPER_MESSAGE)
    }

    private fun printBoard(board: Board) {
        board.cellList.forEach { cell ->
            print(getCellStringByPosition(cell))
        }
        println()
    }

    fun printResult(state: State) {
        (state as Finished).printResult()
    }

    private fun Finished.printResult() {
        when (this) {
            is Win -> {
                println(WIN_GAME)
            }
            is Lose -> {
                println(LOSE_GAME)
            }
        }
    }

    private fun getCellStringByPosition(cell: Cell): String {
        if (cell.isRowStartCell()) {
            return System.lineSeparator() + cell.toDisplayName() + " "
        }
        return cell.toDisplayName() + " "
    }

    private fun Cell.toDisplayName(): String = when (this) {
        is Cell.MineCell -> {
            if (this.isOpen) {
                OPEN_MINE_CELL
            } else {
                NOT_OPEN_CELL_DISPLAY_NAME
            }
        }
        is Cell.SafetyCell -> {
            if (this.isOpen) {
                "$mineCount"
            } else {
                NOT_OPEN_CELL_DISPLAY_NAME
            }
        }
    }
}
