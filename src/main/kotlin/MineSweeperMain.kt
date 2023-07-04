import domain.Board
import domain.Coordinate
import domain.RandomMineCoordinateGenerator
import view.InputView
import view.ResultView
import vo.BoardVO

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val mineCoordinateGenerator = RandomMineCoordinateGenerator(height, width)
    val board = Board.create(height, width, mineCount, mineCoordinateGenerator)

    ResultView.printStartGame()
    playGame(board)
}

private fun playGame(board: Board) {
    while (board.isRunning()) {
        ResultView.printBoard(BoardVO(board))

        val (row, col) = InputView.readCoordinateToOpen()
        val coordinate = Coordinate(row, col)
        if (board.hasMine(coordinate)) {
            ResultView.printLoseGame()
            return
        }
        board.open(coordinate)
    }
    ResultView.printWinGame()
}
