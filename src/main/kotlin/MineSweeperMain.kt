import domain.MineBoard
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()

    val mineBoard = MineBoard.init(height, width)

    ResultView.printMineBoards(mineBoard)
}
