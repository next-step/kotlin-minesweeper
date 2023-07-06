package mine.sweeper

import mine.sweeper.application.MapInitializer
import mine.sweeper.domain.Vulture
import mine.sweeper.view.InputView
import mine.sweeper.view.dto.MapSize

fun main() {
    val mapSize = MapSize(InputView.getHeight(), InputView.getWidth())
    val mineCount = InputView.getMines()
    val minePositions = Vulture(mapSize, mineCount).findMinesPosition()
    val gameController = GameController(MapInitializer(mapSize).create(minePositions))
    gameController.playGame()
}
