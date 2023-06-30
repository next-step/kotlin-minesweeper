import domain.MineBoard
import domain.RandomMinePositionGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineBoard = MineBoard.init(height, width, mineCount, RandomMinePositionGenerator())

    ResultView.printMineBoards(mineBoard)
}
