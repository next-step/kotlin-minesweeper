package minesweeperpractice.controller

import minesweeperpractice.controller.dto.GameMapDisplayDto
import minesweeperpractice.domain.GameMap
import minesweeperpractice.view.InputView
import minesweeperpractice.view.OutputView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun start() {
        // 맵 만들기
        val gameMap = buildMap()

        // 게임 시작
        startGame(gameMap)
    }

    private fun buildMap(): GameMap {
        val buildMapRequest = inputView.createMap()
        return buildMapRequest.toGameMap()
    }

    private fun startGame(gameMap: GameMap) {
        val gameMapDisplayDto = GameMapDisplayDto.from(gameMap)
        outputView.gameStart(gameMapDisplayDto)
    }
}
