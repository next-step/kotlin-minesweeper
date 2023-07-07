package mine.sweeper

import mine.sweeper.application.MapInitializer
import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.Fields
import mine.sweeper.domain.Vulture
import mine.sweeper.view.InputView
import mine.sweeper.view.dto.MapSize

fun main() {
    val mapSize = MapSize(InputView.getHeight(), InputView.getWidth())
    val mineCount = InputView.getMines()
    val minePositions = Vulture(RandomPositionManager(mapSize), mineCount).newMinePositions
    val fields = Fields(mapSize)
    val gameController = GameController(MapInitializer(fields).create(minePositions))
    gameController.playGame()
}
