import domain.Land
import view.InputConsoleView
import view.OutputConsoleView

object MineSweeperApplication {
    private val inputConsoleView = InputConsoleView()
    private val outputConsoleView = OutputConsoleView()

    @JvmStatic
    fun main(args: Array<String>) {
        val height = inputConsoleView.inputHeight()
        val width = inputConsoleView.inputWidth()
        val count = inputConsoleView.inputMineCount()

        val land = Land(height, width, count)
        outputConsoleView.displayLand(land)
    }
}
