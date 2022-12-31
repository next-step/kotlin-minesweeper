package minesweeper.controller

import minesweeper.controller.dto.GameMapDisplayResponse
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

        outputView.displayMap(
            GameMapDisplayResponse.from(gameMap)
        )
    }
}
