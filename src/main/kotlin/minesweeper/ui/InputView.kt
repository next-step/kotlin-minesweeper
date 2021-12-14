package minesweeper.ui

import global.strategy.ui.InputStrategy
import global.strategy.ui.OutputStrategy

class InputView(
    private val inputStrategy: InputStrategy,
    private val outputStrategy: OutputStrategy
) {

    fun askHeight(): Int {
        outputStrategy.execute(ASK_HEIGHT)
        val inputData = inputStrategy.execute()
        return Integer.valueOf(inputData)
    }

    fun askWidth(): Int {
        outputStrategy.execute(ASK_WIDTH)
        val inputData = inputStrategy.execute()
        return Integer.valueOf(inputData)
    }

    fun askMineCount(): Int {
        outputStrategy.execute(ASK_NUMBER_OF_MINES)
        val inputData = inputStrategy.execute()
        return Integer.valueOf(inputData)
    }

    fun askOpenPosition(): String {
        outputStrategy.execute(ASK_OPEN_POSITION)
        return inputStrategy.execute()
    }

    companion object {
        private const val ASK_HEIGHT = "높이를 입력하세요."
        private const val ASK_WIDTH = "너비를 입력하세요."
        private const val ASK_NUMBER_OF_MINES = "지뢰는 몇 개인가요?"
        private const val ASK_OPEN_POSITION = "open: "
    }
}
