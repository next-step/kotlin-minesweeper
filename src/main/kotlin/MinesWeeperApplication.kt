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

    while (true) {
        val inputPosition = InputView.inputOpenPosition()
        val position = OpenPositionParser.parse(inputPosition)

        val isMine = board.isMine(position)

        if (isMine) {
            OutputView.printLose()
            break
        }

        board.open(position)
        OutputView.printBoard(board)
    }
}
