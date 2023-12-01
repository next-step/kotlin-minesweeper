import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val mineSweeper = InputView.prepareMineSweeper()
    ResultView.startMineSweeper(mineSweeper)
}
