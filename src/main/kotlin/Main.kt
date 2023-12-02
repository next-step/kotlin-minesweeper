import domain.MineBoard
import domain.MineConfig
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()

    val mineConfig = MineConfig(
        width = width,
        height = height,
        mineCount = mineCount,
    )

    val mineBoard = MineBoard.of(mineConfig)

    ResultView.printBoard(mineBoard)
}
