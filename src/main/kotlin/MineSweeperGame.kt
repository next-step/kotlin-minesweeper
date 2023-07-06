import domain.MineMap
import view.InputView

class MineSweeperGame {
    fun start() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val numOfMine = InputView.inputMine()
        val mineMap = MineMap(height, width, numOfMine)
    }
}

fun main() {
    val game = MineSweeperGame()
    game.start()
}
