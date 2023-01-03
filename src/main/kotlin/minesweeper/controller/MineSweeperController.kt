package minesweeper.controller

import minesweeper.controller.dto.GameFinishedResponse
import minesweeper.controller.dto.GameMapDisplayResponse
import minesweeper.domain.GameMap
import minesweeper.domain.RandomMineSettingStrategy
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun start() {
        val buildMapRequest = inputView.enterMapRequest()

        val randomSettingStrategy = RandomMineSettingStrategy()
        val gameMap = buildMapRequest.toGameMap(randomSettingStrategy)

        while (gameMap.isFinished().not()) {
            val mapCord = inputView.enterCord().toMapCord()
            gameMap.open(mapCord)

            gameMap.displayResult()
        }
    }

    private fun GameMap.displayResult() {
        if (this.isFinished()) {
            outputView.display(
                GameFinishedResponse.from(this.getGameProgress())
            )
            return
        }
        outputView.display(
            GameMapDisplayResponse.from(this)
        )
    }
}
