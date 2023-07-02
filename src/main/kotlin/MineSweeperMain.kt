import domain.Coordinates
import domain.MineBoard
import domain.RandomMineCoordinateGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val mineBoard = MineBoard.create(height, width, mineCount, mineCoordinateGenerator)
    openMineBoard(height, width, mineBoard)

    ResultView.printMineBoards(mineBoard)
}

private fun openMineBoard(height: Int, width: Int, mineBoard: MineBoard) {
    Coordinates.all(height, width)
        .filter { mineBoard.isClosed(it) }
        .forEach { mineBoard.open(it) }
}
