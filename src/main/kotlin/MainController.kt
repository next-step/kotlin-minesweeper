import domain.generator.RandomFieldGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMineCount()

    val generator = RandomFieldGenerator()
    val field = generator.generate(height, width, mineCount)

    ResultView.printGameStart(field)
}

class MainController
