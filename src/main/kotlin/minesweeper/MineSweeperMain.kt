package minesweeper

import minesweeper.domain.GameStateNotify
import minesweeper.domain.Length
import minesweeper.domain.MineCount
import minesweeper.domain.MineMap
import minesweeper.domain.MinePosition
import minesweeper.domain.TileRow
import minesweeper.view.InputType
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val height = Length.of(InputView.inputDataFromConsole(InputType.HEIGHT))
    val width = Length.of(InputView.inputDataFromConsole(InputType.WIDTH))
    val mineCount = MineCount.of(InputView.inputDataFromConsole(InputType.MINE_COUNT))

    val mineMap = MineMap(width to height, mineCount)
    OutputView.showGameStartMessage()

    mineMap.startGame(object : GameStateNotify {
        override fun getOpenPosition(): MinePosition {
            return InputView.inputOpenPosition()
        }

        override fun showGameState(isWin: Boolean) {
            OutputView.showGameResult(isWin)
        }

        override fun showMineMapInProgress(mineMap: List<TileRow>) {
            OutputView.showMapInProgress(mineMap)
        }
    })
}
