import domain.Game
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()
    val game = Game(height, width, mineCount)

    OutputView.printGameStartMessage()
    OutputView.printMineField(game.getMineFieldState())

    while (true) {
        val (row, col) = InputView.readAskOpenCellMessage()

        val isSafe = game.openCell(row, col)
        OutputView.printMineField(game.getMineFieldState())

        if (!isSafe) {
            OutputView.printGameLoseMessage()
            break
        }

        if (game.isWin()) {
            OutputView.printGameWinMessage()
            break
        }
    }
}
