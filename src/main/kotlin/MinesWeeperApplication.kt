import controller.BoardController
import domain.Height
import domain.Width
import util.OpenPositionParser
import view.InputView
import view.OutputView

fun main() {
    val boardController = BoardController()

    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()

    val board = boardController.create(Width(width), Height(height), mineCount)

    while (true) {
        val inputPosition = InputView.inputOpenPosition()
        val position = OpenPositionParser.parse(inputPosition)

        val isMine = boardController.isMine(board, position)

        if (isMine) {
            OutputView.printLose()
            break
        }

        boardController.open(board, position)

        OutputView.printBoard(board)
    }
}
