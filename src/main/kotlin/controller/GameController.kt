package controller

import domain.model.GameMap
import domain.model.GameOver
import domain.model.MinesweeperGame
import domain.model.Running
import domain.model.Win
import view.InputView
import view.OutputView

class GameController {
    fun execute() {
        try {
            val map = setting()
            play(MinesweeperGame(map))
        } catch (e: IllegalArgumentException) {
            val error = e.message ?: ""
            OutputView.printMessage("$error\n입력된 값이 잘못되어 프로그램을 종료합니다")
        }
    }

    private fun setting(): GameMap {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()
        return GameMap.create(width, height, mineCount)
    }

    private fun play(game: MinesweeperGame) {
        OutputView.printGameStart()
        game.start()
        running(game)
        end(game)
    }

    private fun running(game: MinesweeperGame) {
        while (game.status == Running) {
            val point = InputView.inputPoint(game.map.info.width, game.map.info.height)
            game.playerTurn(point)

            if (game.status == GameOver) return

            OutputView.printMap(game.map)
        }
    }

    private fun end(game: MinesweeperGame) {
        when (game.status) {
            Win -> OutputView.printWin()
            GameOver -> OutputView.printGameOver()
            else -> throw IllegalStateException("지뢰찾기 게임이 종료되지 않았습니다")
        }
    }
}
