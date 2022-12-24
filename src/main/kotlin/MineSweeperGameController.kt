import model.MineSweeperGame
import ui.InputView.inputCols
import ui.InputView.inputMineCount
import ui.InputView.inputRows
import ui.ResultView.resultMineSweeperGame

class MineSweeperGameController {
    fun process() {
        val rows = inputRows()
        val cols = inputCols()
        val mineCount = inputMineCount()

        val mineSweeperGame = MineSweeperGame(rows, cols, mineCount)

        resultMineSweeperGame(mineSweeperGame)
    }
}

fun main() {
    val mineSweeperGameController = MineSweeperGameController()
    mineSweeperGameController.process()
}
