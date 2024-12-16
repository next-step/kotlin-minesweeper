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
            Symbol.ZERO -> "0"
            Symbol.ONE -> "1"
            Symbol.TWO -> "2"
            Symbol.THREE -> "3"
            Symbol.FOUR -> "4"
            Symbol.FIVE -> "5"
            Symbol.SIX -> "6"
            Symbol.SEVEN -> "7"
            Symbol.EIGHT -> "8"
        }
    }
}
