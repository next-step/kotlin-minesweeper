import minesweeper.controller.MinesweeperController
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val inputProvider = InputView()
    val outputConsumer = OutputView()
    MinesweeperController(inputProvider, outputConsumer).start()
}
