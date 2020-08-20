import domain.Board
import view.InputView
import view.ResultView

fun main() {
    val width = InputView.readWidth()
    val height = InputView.readHeight()
    val mineCount = InputView.readMineCount { Board.isValidMineCount(width, height, it) }

    Board(width, height, mineCount)?.run {
        ResultView.printBoard(this)
    }
}
