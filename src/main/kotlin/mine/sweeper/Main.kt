package mine.sweeper

import mine.sweeper.application.FieldsInitializer
import mine.sweeper.application.GameController
import mine.sweeper.application.GameInitializer
import mine.sweeper.domain.GameOption
import mine.sweeper.view.InputView

fun main() {
    val option = GameOption(InputView.getHeight(), InputView.getWidth(), InputView.getMines())
    val fieldList = FieldsInitializer(option).create()
    val game = GameInitializer(fieldList).create()
    val gameController = GameController(game)
    gameController.playGame()
}
