import controller.MinesweeperController
import domain.RandomMinePositionsFactory

fun main() {
    MinesweeperController.startGame(RandomMinePositionsFactory)
}
