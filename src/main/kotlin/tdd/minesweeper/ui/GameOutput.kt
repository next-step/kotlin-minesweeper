package tdd.minesweeper.ui

import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.domain.type.SymbolType

interface GameOutput {
    fun printMineBoard(response: List<List<SymbolType>>)
    fun printGameStart()
    fun printFinished(status: GameProgressStatus)
    fun printException(message: String?)
}
