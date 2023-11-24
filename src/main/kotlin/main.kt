import controller.MinesweeperGame
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val minesweeperGame = MinesweeperGame(inputView, outputView)

    minesweeperGame.startGame()
}
