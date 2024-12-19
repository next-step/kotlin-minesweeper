package view

import domain.Game
import domain.MineFieldState
import domain.Cell
import domain.CellId

object OutputView {
    private const val GAME_START_MESSAGE = "\n지뢰찾기 게임 시작"
    private const val CELL_ID_EMPTY_DISPLAY = "0"
    private const val CELL_ID_MINE_DISPLAY = "*"

    fun printGameStartMessage() {
        println(GAME_START_MESSAGE)
    }

    fun printMineField(game: Game) {
        val state = game.getMineFieldState()
        renderMineField(state).forEach { println(it) }
    }

    private fun renderMineField(state: MineFieldState): List<String> {
        return state.cells.getRows().map { row ->
            row.getCells()
                .joinToString(" ") { cell ->
                    mapCellToDisplay(cell)
                }
        }
    }

    private fun mapCellToDisplay(cell: Cell): String =
        when (cell.id) {
            CellId.EMPTY -> CELL_ID_EMPTY_DISPLAY
            CellId.MINE -> CELL_ID_MINE_DISPLAY
            CellId.NUMBER -> (cell as Cell.NumberCell).count.toString()
        }
}
