package minesweeper.controller

import minesweeper.domain.land.Land
import minesweeper.domain.land.LandSize
import minesweeper.view.InputView
import minesweeper.view.ResultView

class GameApp(val land: Land) {
    companion object {
        fun generateMines(
            total: Int,
            count: Int,
        ): List<Int> {
            return (0..<total)
                .shuffled()
                .take(count)
        }
    }
}

fun main() {
    val landSize = LandSize(InputView.inputHeight(), InputView.inputWidth())
    val mineCount = InputView.inputMineCount()
    val game =
        GameApp(
            Land.from(
                landSize,
                mineCount,
                GameApp::generateMines,
            ),
        )

    val land = game.land
    ResultView.showLand(land)
}
