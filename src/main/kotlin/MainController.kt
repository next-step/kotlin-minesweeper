import domain.GameStatus
import domain.generator.RandomFieldGenerator
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()

    val generator = RandomFieldGenerator()
    val field = generator.generate(height, width, mineCount)

    ResultView.printGameStartWording()
    var result: GameStatus = GameStatus.PROGRESSING

    while (result.isProgressing) {
        val coordinate = InputView.readOpenCoordinate()
        result = field.openBlock(coordinate[0], coordinate[1])
        ResultView.printGameField(field)
    }

    ResultView.printGameResult(result)
}
