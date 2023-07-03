import domain.Board
import domain.Coordinate
import domain.RandomMineCoordinateGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val board = Board.create(height, width, mineCount, mineCoordinateGenerator)

    ResultView.printStartGame()
    val isClear = playGame(board)
    if (isClear) ResultView.printWinGame()
}

private fun playGame(board: Board): Boolean {
    while (board.isRunning()) {
        ResultView.printBoard(board)

        val (row, col) = InputView.readCoordinateToOpen()
        val coordinate = Coordinate(row, col)
        if (board.hasMine(coordinate)) {
            ResultView.printLoseGame()
            return false
        }
        board.open(coordinate)
    }
    return true
}
