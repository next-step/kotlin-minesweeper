import domain.GameInfo
import view.InputView

fun main() {
    val vertical = InputView.getVertical()
    val horizontal = InputView.getHorizontal()
    val mineCount = InputView.getMineCount()

    val gameInfo = GameInfo(vertical, horizontal, mineCount)
    println(gameInfo)
}
