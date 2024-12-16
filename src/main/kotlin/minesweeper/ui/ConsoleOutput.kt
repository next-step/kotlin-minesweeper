package minesweeper.ui

import minesweeper.domain.Row
import minesweeper.domain.Symbol

object ConsoleOutput {
    private const val DELIMITER = " "

    fun announceGameStarted() = println("지뢰찾기 게임 시작")

    fun displayCurrentGameBoard(rows: List<Row>) {
        for (row in rows) {
            row.cells().joinToString(DELIMITER) { display(it.symbol()) }.let(::print)
            println()
        }
    }

    private fun display(symbol: Symbol): String {
        return when (symbol) {
            Symbol.LANDMINE -> "*"
            Symbol.CLOSED -> "■"
        }
    }
}
