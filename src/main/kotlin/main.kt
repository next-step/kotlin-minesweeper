import controller.MinesweeperGame
import domain.AdjacentMineCounter
import domain.MineManager
import domain.ShuffledMinePlacer
import inteface.RandomMinePlacementStrategy
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val minePlacementStrategy = RandomMinePlacementStrategy()
    val minePlacer = ShuffledMinePlacer()
    val mineCounter = AdjacentMineCounter()
    val mineManager = MineManager(minePlacementStrategy, minePlacer, mineCounter)

    val minesweeperGame = MinesweeperGame(inputView, outputView, mineManager)
    minesweeperGame.startGame()
}
