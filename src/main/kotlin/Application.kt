import domain.BoardGenerator
import view.InputView
import view.ResultView

fun main() {
    val width = InputView.readWidth()
    val height = InputView.readHeight()
    val mineCount = InputView.readMineCount { BoardGenerator.getOrNull(width, height, it) != null }

    BoardGenerator.getOrNull(width, height, mineCount)?.run {
        ResultView.printStart()
        Minesweeper(width, height, this.execute()).start()
    }
}
