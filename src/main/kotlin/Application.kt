import domain.MineSweeperGame
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.inputHeight()
    val weight = InputView.inputWidth()
    val totalBoardCount = height * weight
    val mineCount = InputView.inputMine(MineSweeperGame.validateMineCount(totalBoardCount))
    val board = MineSweeperGame.initBoard(weight, height, mineCount)
    OutputView.drawMine(board)
}
