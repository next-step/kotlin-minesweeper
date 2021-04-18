package controller

import domain.square.mine.MineFactory
import ui.InputView
import ui.ResultView

object MineSweeperController {
    fun run() {
        val gameData = InputView.askInfo()

        val mineFactory = MineFactory()
        val mines = mineFactory.createMines(gameData)

        ResultView(gameData).printMineGameState(mines)
    }
}
