package controller

import domain.MineFactory
import model.GameData
import ui.InputView
import ui.ResultView

object MineSweeperController {
    fun run() {
        val height = InputView.askHeight()
        val width = InputView.askWidth()
        val mineNumber = InputView.askMineNumber()
        val gameData = GameData(width, height, mineNumber)

        val mineFactory = MineFactory()
        val mines = mineFactory.createMines(gameData)

        ResultView.printMineGameState(mines, gameData)
    }
}
