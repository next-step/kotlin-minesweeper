package minesweeper.controller

import minesweeper.controller.dto.GameMapDisplayResponse
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun start() {
        val buildMapRequest = inputView.enterMapRequest()

        val gameMap = buildMapRequest.toGameMap()

        outputView.displayMap(
            GameMapDisplayResponse.from(gameMap)
        )
    }
}
