import controller.MinesweeperGame
import domain.AdjacentMineCounter
import domain.MineManager
import inteface.RandomMinePlacementStrategy
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val minePlacementStrategy = RandomMinePlacementStrategy()
    val mineCounter = AdjacentMineCounter()
    val mineManager = MineManager(minePlacementStrategy, mineCounter)

    val minesweeperGame = MinesweeperGame(inputView, outputView, mineManager)
    minesweeperGame.startGame()
}
