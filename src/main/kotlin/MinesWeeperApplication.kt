import controller.BoardController
import domain.Height
import domain.Width
import util.OpenPositionParser
import view.InputView
import view.OutputView

fun main() {
    val boardCreateController = BoardController()

    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()

    val board = boardCreateController.create(Width(width), Height(height), mineCount)

    OutputView.printBoard(board)

    while (true) {
        val openPosition = InputView.inputOpenPosition()
        val input = OpenPositionParser.parse(openPosition)
    }
}
