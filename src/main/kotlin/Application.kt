import domain.Board
import view.InputView

fun main() {
    val width = InputView.readWidth()
    val height = InputView.readHeight()
    val mineCount = InputView.readMineCount { Board.isValidMineCount(width, height, it) }

    Board.getOrNull(width, height, mineCount)?.run {
        Minesweeper(this).start()
    }
}
