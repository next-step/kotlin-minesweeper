package minesweeper.view.output

import minesweeper.model.board.Board
import minesweeper.model.board.Cell
import minesweeper.model.board.Cells
import minesweeper.model.board.coordinate.rangeTo

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"

    override fun printInitialMessage() = println(initialMessage)

    override fun printBoard(board: Board) {
        println(board.toPrintableString())
    }

    private fun Board.toPrintableString(): String {
        val rowCount = this.rowCount
        return (0..rowCount).mapNotNull(::cellsAtRowOrNull)
            .joinToString(separator = "\n") { cells -> cells.toPrintableString() }
    }

    private fun Cells.toPrintableString(): String =
        this.joinToString(separator = "") { cell -> cell.toPrintableString() }

    private fun Cell.toPrintableString(): String = when (this) {
        is Cell.Mine -> "*"
        is Cell.Safe -> "C"
    }
}
