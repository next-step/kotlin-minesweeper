package minesweeper.view.output

import minesweeper.model.board.Board
import minesweeper.model.board.BoardState
import minesweeper.model.cell.Cell
import minesweeper.model.cell.Cells
import minesweeper.model.coordinate.rangeTo

object ConsoleOutputView : OutputView {

    private const val initialMessage = "지뢰찾기 게임 시작"
    private const val PRINTABLE_STRING_FOR_CLOSE_CELL = "C"

    override fun printInitialMessage() = println(initialMessage)

    override fun printBoard(board: Board) {

        println(board.toPrintableString())

        val boardState = board.state
        if (boardState is BoardState.Finished) {
            printFinalMessage(boardState.isWin)
        }
    }

    private fun printFinalMessage(isWin: Boolean) {
        val message = if (isWin) "Win Game" else "Lose Game"
        println(message)
    }

    private fun Board.toPrintableString(): String {
        val rowCount = this.rowCount
        return (0..rowCount).mapNotNull { this.cellsAtRowOrNull(it) }
            .joinToString(separator = "\n") { cells -> cells.toPrintableString() }
    }

    private fun Board.cellsAtRowOrNull(row: Int): Cells? = runCatching {
        Cells(this.cells.filter { it.row == row })
    }.getOrNull()

    private fun Cells.toPrintableString(): String =
        this.joinToString(separator = "") { cell -> cell.toPrintableString() }

    private fun Cell.toPrintableString(): String = when (this) {
        is Cell.Mine -> this.toPrintableString()
        is Cell.Safe -> this.toPrintableString()
    }

    private fun Cell.Mine.toPrintableString(): String =
        if (this.isOpen) "*" else PRINTABLE_STRING_FOR_CLOSE_CELL

    private fun Cell.Safe.toPrintableString(): String =
        if (this.isOpen) "${this.surroundMineCount}" else PRINTABLE_STRING_FOR_CLOSE_CELL
}
