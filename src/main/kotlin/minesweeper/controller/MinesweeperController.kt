package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MinesweeperController {
    fun start() {
        val settingInformation = InputView.inputSettingInformation()
        val board = Board(
            height = settingInformation.height,
            width = settingInformation.width,
            mineCount = settingInformation.mineCount,
        )

        OutputView.printGameStartMessage()
        OutputView.printBoard(board)
    }
}
