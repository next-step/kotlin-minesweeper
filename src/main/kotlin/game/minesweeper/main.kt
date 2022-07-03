package game.minesweeper

import game.minesweeper.domain.GameController
import game.minesweeper.view.InputView
import game.minesweeper.view.ResultView

fun main() {
    val controller = GameController(InputView.readHeight(), InputView.readWeight(), InputView.readMineCount())
    ResultView.drawMap(controller.rows)
}
