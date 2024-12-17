import domain.Game
import view.InputView
import view.OutputView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readMineCount()
    val game = Game(height, width, mineCount)

    OutputView.printGameStartMessage()
    game.start()
}
