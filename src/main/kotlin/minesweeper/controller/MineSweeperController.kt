package minesweeper.controller

import minesweeper.controller.dto.GameMapDisplayDto
import minesweeper.view.InputView
import minesweeper.view.OutputView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun start() {
        // 맵 만들기
        val buildMapRequest = inputView.createMap()
        val gameMap = buildMapRequest.toGameMap()

        // 게임 시작
        val gameMapDisplayDto = GameMapDisplayDto.from(gameMap)
        outputView.gameStart(gameMapDisplayDto)
    }
}
