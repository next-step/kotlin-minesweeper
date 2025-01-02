import view.InputView
import view.OutputView

class MinesweeperApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val size = Size(width = width, height = height)
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
