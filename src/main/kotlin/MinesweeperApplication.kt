import view.InputView
import view.OutputView

class MinesweeperApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        outputView.showInputHeight()
        val height = inputView.inputHeight()

        outputView.showInputWidth()
        val width = inputView.inputWidth()

        outputView.showInputMineCount()
        val mineCount = inputView.inputMineCount()

        val size = Size(height, width)
        val minesweeper = Minesweeper(size, mineCount)
        outputView.showMinesweeper(minesweeper)
    }
}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val minesweeperApplication = MinesweeperApplication(inputView, outputView)
    minesweeperApplication.run()
}
