package controller

import domain.GameResult
import domain.MineSweeperGame
import domain.MineSweeperMap
import domain.position.RandomMinePositionGenerator
import view.input.HeightInputView
import view.input.MineCountInputView
import view.input.OpenPositionInputView
import view.input.WidthInputView
import view.output.GameEndOutputView
import view.output.GameMapOutputView
import view.output.GameStartOutputView

class MineSweeperController {
    private lateinit var mineSweeperGame: MineSweeperGame
    fun play() {
        init()
        val gameResult = start()
        end(gameResult)
    }

    private fun init() {
        val height = HeightInputView().value
        val width = WidthInputView().value
        val mineCount = MineCountInputView(height, width).value
        val mapProperty = MineSweeperMap.Property(height, width)
        val minePositionGenerator = RandomMinePositionGenerator.of(mapProperty, mineCount)
        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)

        mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)
    }

    private fun start(): GameResult {
        GameStartOutputView()
        do {
            val result = startRound()
            if (result) GameMapOutputView(mineSweeperGame.mineSweeperMap)
        } while (!mineSweeperGame.isEnd())

        return mineSweeperGame.getResult()
    }

    private fun startRound(): Boolean {
        val inputPosition = OpenPositionInputView().value
        return mineSweeperGame.open(inputPosition)
    }

    private fun end(result: GameResult) {
        GameEndOutputView(result)
    }
}
