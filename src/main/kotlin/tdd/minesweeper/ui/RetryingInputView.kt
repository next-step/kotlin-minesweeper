package tdd.minesweeper.ui

import tdd.minesweeper.domain.Location

class RetryingInputView(private val delegate: InputView) : InputView {
    override fun inputHeight(): Int = retry { delegate.inputHeight() }

    override fun inputWidth(): Int = retry { delegate.inputWidth() }

    override fun inputCountOfMines(): Int = retry { delegate.inputCountOfMines() }

    override fun inputLocation(): Location = retry { delegate.inputLocation() }

    private fun <T> retry(input: () -> T): T {
        var result: Result<T>
        do {
            result = runCatching { input() }
            result.exceptionOrNull()?.let {
                println(it.message)
                println()
            }
        } while (result.isFailure)
        return result.getOrThrow()
    }
}
