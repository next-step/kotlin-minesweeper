package tdd.minesweeper.ui

import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.domain.type.SymbolType

object GameOutputImpl : GameOutput {

    override fun printMineBoard(response: List<List<SymbolType>>) {
        buildString {
            response.forEach { symbolTypes ->
                append("\n")
                append(symbolTypes.joinToString(separator = " ") { convertToString(it) })
            }
        }.run(::println)
    }

    private fun convertToString(symbol: SymbolType): String = when (symbol) {
        SymbolType.BLIND -> "C"
        SymbolType.ZERO -> "0"
        SymbolType.MINE -> "*"
        SymbolType.ONE -> "1"
        SymbolType.TWO -> "2"
        SymbolType.THREE -> "3"
        SymbolType.FOUR -> "4"
        SymbolType.FIVE -> "5"
        SymbolType.SIX -> "6"
        SymbolType.SEVEN -> "7"
        SymbolType.EIGHT -> "8"
    }

    override fun printGameStart() {
        print("\n지뢰찾기 게임 시작\n")
    }

    override fun printFinished(status: GameProgressStatus) = when (status) {
        GameProgressStatus.WIN -> println("\nWin Game.")
        GameProgressStatus.LOSE -> println("\nLose Game.")
        else -> println("\ngame progress....")
    }

    override fun printException(message: String?) {
        message?.run(::println)
    }
}
