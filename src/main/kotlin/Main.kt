import domain.FieldSize
import domain.MineSweeperGame
import ui.InputView.readInputForHeight
import ui.InputView.readInputForNumberOfMines
import ui.InputView.readInputForWidth
import ui.OutputView.displayMineField
import ui.OutputView.displayRequestHeight
import ui.OutputView.displayRequestNumberOfMines
import ui.OutputView.displayRequestWidth
import ui.OutputView.displayGameStartTitle

fun main() {
    val mineSweeperGame = requestForMakeNewSweeperGame()

    displayGameStartTitle()
    displayMineField(mineSweeperGame.mineField)
}

fun requestForMakeNewSweeperGame(): MineSweeperGame {
    displayRequestHeight()
    val height = readInputForHeight()

    displayRequestWidth()
    val width = readInputForWidth()
    val fieldSize = FieldSize(width, height)

    displayRequestNumberOfMines()
    val numberOfMines = readInputForNumberOfMines(width * height)

    return MineSweeperGame.newGame(fieldSize, numberOfMines)
}
