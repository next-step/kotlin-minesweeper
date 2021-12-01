package minesweeper.ui

import global.strategy.OutputStrategy

class ErrorView(private val outputStrategy: OutputStrategy) {
    fun alert(errorMessage: String): Unit = outputStrategy.execute(errorMessage)
}
