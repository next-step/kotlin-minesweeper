package minesweeper

import minesweeper.domain.GameStateNotify
import minesweeper.domain.GameStatus
import minesweeper.domain.MineGenerator
import minesweeper.domain.MinePosition
import minesweeper.domain.MinePositionGenerator
import minesweeper.domain.MinesMap
import minesweeper.domain.MinesWeeperGame
import minesweeper.domain.NumberValue
import minesweeper.view.InputType
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {

    val height = NumberValue.of(InputView.inputDataFromConsole(InputType.HEIGHT))
    val width = NumberValue.of(InputView.inputDataFromConsole(InputType.WIDTH))
    val mineCount = NumberValue.of(InputView.inputDataFromConsole(InputType.MINE_COUNT))

    val minesWeeperGame = MinesWeeperGame(width to height, mineCount)
    minesWeeperGame.generateMine(object : MinePositionGenerator {
        override fun generatePosition(): MinePosition {
            return MineGenerator.generate(height, width)
        }
    })
    OutputView.showGameStartMessage()

    minesWeeperGame.openTile(object : GameStateNotify {
        override fun getOpenPosition(): MinePosition {
            return InputView.inputOpenPosition()
        }

        override fun isContinueGame(): Boolean {
            return true
        }

        override fun showGameState(status: GameStatus) {
            OutputView.showGameResult(status)
        }

        override fun showMineMapInProgress(mineMap: MinesMap) {
            OutputView.showMapInProgress(mineMap)
        }
    })
}
