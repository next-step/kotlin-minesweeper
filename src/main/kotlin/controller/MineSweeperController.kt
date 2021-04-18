package controller

import domain.Board
import ui.InputView
import ui.ResultView

object MineSweeperController {
    fun run() {
        val gameData = InputView.askInfo()
        val board = Board(gameData)

        ResultView(gameData).printMineGameState(board.squares)
    }
}
