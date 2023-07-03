import domain.Board
import domain.Coordinates
import domain.RandomMineCoordinateGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val board = Board.create(height, width, mineCount, mineCoordinateGenerator)
    openMineBoard(height, width, board)

    ResultView.printBoard(board)
}

private fun openMineBoard(height: Int, width: Int, board: Board) {
    Coordinates.all(height, width)
        .filter { board.isClosed(it) }
        .forEach { board.open(it) }
}
