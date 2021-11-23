import minesweeper.controller.MineSweeperController
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    MineSweeperController().start(InputView, ResultView)
}
