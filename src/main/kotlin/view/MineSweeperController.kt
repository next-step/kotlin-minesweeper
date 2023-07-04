package view

import domain.game.MineSweeperGameFactory

class MineSweeperController(
    private val inputView: MineSweeperInputView,
    private val resultView: MineSweeperResultView,
    private val mineSweeperGameFactory: MineSweeperGameFactory,
) {

    fun start() {
        val mineSweeperInitProperty = inputView.readInitProperty()
        val mineSweeperGame = mineSweeperGameFactory.create(mineSweeperInitProperty)
        resultView.displayStartMineSweeperGameMessage()
        inputView.readOpenCoordinate()
    }
}
