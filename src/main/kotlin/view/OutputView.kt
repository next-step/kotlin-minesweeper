package view

import domain.Cell
import domain.CellId
import domain.MineFieldState

object OutputView {
    private const val GAME_START_MESSAGE = "\n지뢰찾기 게임 시작"
    private const val CELL_ID_EMPTY_DISPLAY = "0"
    private const val CELL_ID_MINE_DISPLAY = "*"
    private const val CELL_CLOSED_DISPLAY = "C"
    private const val GAME_LOSE_MESSAGE = "Lose Game."
    private const val GAME_WIN_MESSAGE = "Win!"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printMineField(state: MineFieldState) {
        renderMineField(state).forEach { println(it) }
    }

    private fun renderMineField(state: MineFieldState): List<String> {
        return state.cells.rows.map { row ->
            row.getCells()
                .joinToString(" ") { cell ->
                    mapCellToDisplay(cell)
                }
        }
    }

    private fun mapCellToDisplay(cell: Cell): String {
        return if (cell.isOpen) {
            when (cell.id) {
                CellId.EMPTY -> CELL_ID_EMPTY_DISPLAY
                CellId.MINE -> CELL_ID_MINE_DISPLAY
                CellId.NUMBER -> (cell as Cell.NumberCell).count.toString()
            }
        } else {
            CELL_CLOSED_DISPLAY
        }
    }

    fun printGameLoseMessage() {
        println(GAME_LOSE_MESSAGE)
    }

    fun printGameWinMessage() {
        println(GAME_WIN_MESSAGE)
    }
}
