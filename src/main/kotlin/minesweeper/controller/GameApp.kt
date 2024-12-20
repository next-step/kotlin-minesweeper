package minesweeper.controller

import minesweeper.view.InputView
import minesweeper.view.ResultView

class GameApp {
    var land: Land? = null

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
    val game = GameApp()
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()
    game.land =
        Land.from(
            height,
            width,
            mineCount,
            GameApp::generateMines,
        )

    val land = game.land ?: return
    ResultView.showLand(land)
}
