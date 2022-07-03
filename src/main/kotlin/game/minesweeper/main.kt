package game.minesweeper

import game.minesweeper.domain.GameController
import game.minesweeper.domain.map.MapConfig
import game.minesweeper.view.InputView
import game.minesweeper.view.ResultView

fun main() {
    val config = MapConfig(InputView.readHeight(), InputView.readWeight())
    val controller = GameController(config, InputView.readMineCount())
    ResultView.drawMap(controller.rows)
}
