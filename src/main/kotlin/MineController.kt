import domain.Board
import domain.RandomMineGenerator
import ui.InputView
import ui.OutputView

fun main() {
    val height = InputView.askHeight()
    val width = InputView.askWidth()
    val mineNumber = InputView.askMineNumber()

    val mineGenerator = RandomMineGenerator(mineNumber, width * height)
    val board = Board(width, height, mineGenerator)
    OutputView.printGameStartMsg()
    OutputView.printBoard(board)
}
