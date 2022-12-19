package minesweeper.controller

import minesweeper.view.InputView

object Controller {
    fun start() {
        val height = InputFilter.inputPosition(InputView.INPUT_HEIGHT_MESSAGE)
        val width = InputFilter.inputPosition(InputView.INPUT_WIDTH_MESSAGE)
        val mineCount = InputFilter.inputMineCount(InputView.INPUT_MINE_COUNT_MESSAGE, height.getCalibratedPosition() * width.getCalibratedPosition())
    }
}
