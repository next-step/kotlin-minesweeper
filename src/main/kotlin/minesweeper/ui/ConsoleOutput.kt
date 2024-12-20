package minesweeper.ui

import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Symbol

object ConsoleOutput {
    private const val DELIMITER = " "

    fun announceGameStarted() = println("지뢰찾기 게임 시작")

    fun printException(exception: Throwable) = println(exception.message)

    fun announceGameWin() = println("Win Game!!")

    fun announceGameLose() = println("Lose Game.")

    fun displayCurrentGameBoard(gameBoard: GameBoard) {
        gameBoard.cells
            .chunked(gameBoard.area.width)
            .forEach { row ->
                println(row.joinToString(DELIMITER) { display(it.symbol) })
            }
        println()
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
