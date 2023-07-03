package controller

import domain.model.GameMap
import view.InputView
import view.OutputView

class GameController {
    fun execute() {
        try {
            val map = input()
            output(map)
        } catch (e: IllegalArgumentException) {
            val error = e.message ?: ""
            OutputView.printMessage("$error\n입력된 값이 잘못되어 프로그램을 종료합니다")
        }
    }

    private fun input(): GameMap {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()
        return GameMap.create(width, height, mineCount, true)
    }

    private fun output(map: GameMap) {
        OutputView.printGameStart(map)
    }
}
