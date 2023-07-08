import domain.MineMapGenerator
import view.DisplayView
import view.InputView

class MineSweeperGame {
    fun start() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val numOfMine = InputView.inputMine()

        val mineMap = MineMapGenerator.createMineMap(height, width, numOfMine)

        DisplayView.displayMap(mineMap)
    }
}

fun main() {
    val game = MineSweeperGame()
    game.start()
}
