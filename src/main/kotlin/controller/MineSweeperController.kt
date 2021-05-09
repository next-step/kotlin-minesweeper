package controller

import domain.MineSweeperGame
import ui.InputView
import ui.ResultView

object MineSweeperController {
    fun run() {
        val gameData = InputView.askInfo()
        val mineSweeperGame = MineSweeperGame(gameData)

        val resultView = ResultView(gameData)
        resultView.printStart()

        do {
            resultView.printBoard(mineSweeperGame.board)

            val openPosition = InputView.askOpenPosition()
            mineSweeperGame.open(openPosition)
        } while (!mineSweeperGame.hasDone)

        if (mineSweeperGame.hasLose) {
            resultView.printLose()
        } else {
            resultView.printWin()
        }
    }
}
