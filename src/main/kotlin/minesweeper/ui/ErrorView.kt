package minesweeper.ui

import global.strategy.ui.OutputStrategy

class ErrorView(private val outputStrategy: OutputStrategy) {
    fun alert(errorMessage: String): Unit = outputStrategy.execute(errorMessage)
}
