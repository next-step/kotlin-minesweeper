package minesweeper.controller

import minesweeper.domain.MineSweeperGame
import minesweeper.domain.Position
import minesweeper.domain.minemap.MineMap
import minesweeper.domain.minemap.MineMapConfig
import minesweeper.view.InputView
import minesweeper.view.ResultView

class MineSweeperController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {
    fun runGame() {
        val height = inputView.inputMapHeight()
        val width = inputView.inputMapWidth()
        val mineCount = inputView.inputMapMineCount()
        val mineMapConfig = MineMapConfig(height, width, mineCount)

        val openPositionInput: () -> Position = { inputView.inputOpenPosition().let { Position(it.first, it.second) }}
        val gameProgressOutput: (MineMap) -> Unit = { mineMap -> resultView.outputMap(mineMap)}

        resultView.outputGameStart()
        val mineSweeperGame = MineSweeperGame(
            mineMapConfig =  mineMapConfig,
            openPosition = openPositionInput,
            gameProgress = gameProgressOutput,
        )
        val gameResult = mineSweeperGame.run()
        resultView.outputResult(gameResult.win)
    }
}
