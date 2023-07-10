package minesweeper2

import minesweeper2.domain.GameStateNotify
import minesweeper2.domain.Length
import minesweeper2.domain.MineCount
import minesweeper2.domain.MineMap
import minesweeper2.domain.MinePosition
import minesweeper2.domain.TileRow
import minesweeper2.view.InputType
import minesweeper2.view.InputView
import minesweeper2.view.OutputView

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
