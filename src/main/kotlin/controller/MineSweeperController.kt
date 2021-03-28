package controller

import domain.MineFactory
import ui.InputView
import ui.ResultView

object MineSweeperController {
    fun run() {
        val gameData = InputView.askInfo()

        val mineFactory = MineFactory()
        val mines = mineFactory.createMines(gameData)

        ResultView.printMineGameState(mines, gameData)
    }
}
