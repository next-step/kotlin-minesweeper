package view

import domain.game.MineSweeperGameFactory
import domain.game.OpenResult

class MineSweeperController(
    private val inputView: MineSweeperInputView,
    private val resultView: MineSweeperResultView,
    private val mineSweeperGameFactory: MineSweeperGameFactory,
) {

    fun start() {
        val mineSweeperInitProperty = inputView.readInitProperty()
        resultView.displayStartMineSweeperGameMessage()

        val mineSweeperGame = mineSweeperGameFactory.create(mineSweeperInitProperty)

        do {
            val coordinate = inputView.readOpenCoordinate()
            val openResult = mineSweeperGame.open(coordinate)
            when (openResult) {
                is OpenResult.MineOpened -> resultView.displayLoseGameMessage()
                is OpenResult.GroundOpened -> resultView.display(openResult.cells)
                is OpenResult.AllMineFound -> resultView.displayWinGameMessage()
            }
        } while (openResult.isGameFinished.not())
    }
}
