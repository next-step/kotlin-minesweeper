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

        while (true) {
            val coordinate = inputView.readOpenCoordinate()
            when (mineSweeperGame.open(coordinate)) {
                is OpenResult.MineOpened -> {
                    resultView.displayLoseGameMessage()
                    return
                }
            }
        }
    }
}
