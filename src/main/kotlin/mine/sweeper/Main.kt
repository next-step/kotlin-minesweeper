package mine.sweeper

import mine.sweeper.domain.MapSize
import mine.sweeper.view.InputView

fun main() {
    val mapSize = MapSize(height = InputView.getHeight(), width = InputView.getWidth())
    val sweeperGame = MineSweeperGame(mapSize, mineCount = InputView.getMines())

    sweeperGame.printEntireMap()
}
