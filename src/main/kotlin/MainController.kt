import domain.generator.RandomFieldGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val generator = RandomFieldGenerator()
    val field = generator.generate(height, width, mineCount)

    ResultView.printGameStart(field)
}
