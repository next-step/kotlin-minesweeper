package minesweeper.controller

import minesweeper.view.InputView
import minesweeper.view.ResultView

class GameApp {
    var land: Land? = null
}

fun main() {
    val game = GameApp()
    game.land =
        Land.from(
            InputView.inputHeight(),
            InputView.inputWidth(),
            InputView.inputMineCount(),
        )

    val land = game.land ?: return
    ResultView.showLand(land)
}
