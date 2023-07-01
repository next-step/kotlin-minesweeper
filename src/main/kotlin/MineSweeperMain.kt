import domain.MineBoard
import domain.RandomMineCoordinateGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount(height * width)

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val mineBoard = MineBoard.create(height, width, mineCount, mineCoordinateGenerator)

    ResultView.printMineBoards(mineBoard)
}
