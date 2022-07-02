import domain.Board
import domain.BoardGenerator
import domain.GameInfo
import view.InputView
import view.OutputView

fun main() {
    val vertical = InputView.getVertical()
    val horizontal = InputView.getHorizontal()
    val mineCount = InputView.getMineCount()

    val gameInfo = GameInfo(vertical, horizontal, mineCount)
    val board = Board(BoardGenerator(gameInfo))

    OutputView.printMinesweeperBoard(board, gameInfo)
}
