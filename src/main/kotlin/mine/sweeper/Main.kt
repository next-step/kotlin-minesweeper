package mine.sweeper

import mine.sweeper.application.MapInitializer
import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.Vulture
import mine.sweeper.view.InputView
import mine.sweeper.view.dto.MapSize

fun main() {
    val mapSize = MapSize(InputView.getHeight(), InputView.getWidth())
    val mineCount = InputView.getMines()
    val minePositions = Vulture(RandomPositionManager(mapSize), mineCount).newMinePositions
    val gameController = GameController(MapInitializer(mapSize, minePositions).create())
    gameController.playGame()
}
