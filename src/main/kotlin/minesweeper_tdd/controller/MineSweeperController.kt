package minesweeper_tdd.controller

import minesweeper_tdd.domain.MineSweeperGame
import minesweeper_tdd.domain.OnGameProgressed
import minesweeper_tdd.domain.OnPositionProduced
import minesweeper_tdd.domain.Position
import minesweeper_tdd.domain.minemap.MineMapConfig
import minesweeper_tdd.view.InputView
import minesweeper_tdd.view.ResultView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {
    fun runGame() {
        val height = inputView.inputMapHeight()
        val width = inputView.inputMapWidth()
        val mineCount = inputView.inputMapMineCount()
        val mineMapConfig = MineMapConfig(height, width, mineCount)

        val openPositionInput: OnPositionProduced = { inputView.inputOpenPosition().let { Position(it.first, it.second) } }
        val gameProgressOutput: OnGameProgressed = { mineMap -> resultView.outputMap(mineMap) }

        resultView.outputGameStart()
        val mineSweeperGame = MineSweeperGame(
            mineMapConfig = mineMapConfig,
            onPositionProduced = openPositionInput,
            onGameProgressed = gameProgressOutput,
        )
        val gameResult = mineSweeperGame.run()
        resultView.outputResult(gameResult.win)
    }
}
