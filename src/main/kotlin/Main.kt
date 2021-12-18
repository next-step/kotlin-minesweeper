import domain.FieldSize
import domain.MineSweeperGame
import domain.Point
import ui.InputView.readInputForHeight
import ui.InputView.readInputForNumberOfMines
import ui.InputView.readInputForRequestPointForCheck
import ui.InputView.readInputForWidth
import ui.OutputView.displayMineField
import ui.OutputView.displayRequestHeight
import ui.OutputView.displayRequestNumberOfMines
import ui.OutputView.displayRequestWidth
import ui.OutputView.displayGameStartTitle
import ui.OutputView.displayLoseGame
import ui.OutputView.displayRequestPointForCheck
import ui.OutputView.displayWinGame

fun main() {
    val mineSweeperGame = requestForMakeNewSweeperGame()
    displayGameStartTitle()
    displayMineField(mineSweeperGame.allSlots())
    mineSweeperGame.play()
}

fun MineSweeperGame.play() {
    var mineSelected = false
    var allGroundCheck = false
    while (!allGroundCheck && !mineSelected) {
        val requestPoint = requestMineCheckPoint()
        mineSelected = checkIsMineAt(requestPoint)
        displayMineField(allSlots())
        allGroundCheck = isAllGroundChecked()
    }
    displayGameResult(allGroundCheck)
}

fun requestMineCheckPoint(): Point {
    displayRequestPointForCheck()
    return readInputForRequestPointForCheck()
}

fun displayGameResult(gameResult: Boolean) {
    if (gameResult) {
        displayWinGame()
        return
    }
    displayLoseGame()
}

fun requestForMakeNewSweeperGame(): MineSweeperGame {
    val fieldSize = requestSize()
    val numberOfMines = requestNumberOfMines(fieldSize)

    return MineSweeperGame.newGame(fieldSize, numberOfMines)
}

fun requestSize(): FieldSize {
    displayRequestHeight()
    val height = readInputForHeight()
    displayRequestWidth()
    val width = readInputForWidth()
    return FieldSize(width, height)
}

fun requestNumberOfMines(fieldSize: FieldSize): Int {
    displayRequestNumberOfMines()
    return readInputForNumberOfMines(fieldSize.width * fieldSize.height)
}
