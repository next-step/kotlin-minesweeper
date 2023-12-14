import minesweeper.MinesweeperController
import minesweeper.view.InputView

fun main() {
    val inputProvider = InputView()
    MinesweeperController(inputProvider).start()
}
