import controller.MinesweeperGame
import inteface.RandomMinePlacementStrategy
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val minePlacementStrategy = RandomMinePlacementStrategy()

    val minesweeperGame = MinesweeperGame(inputView, outputView, minePlacementStrategy)
    minesweeperGame.startGame()
}
