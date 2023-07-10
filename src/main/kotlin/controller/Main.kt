package controller

import domain.MinesWeeper
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCounts = InputView.getMineCounts()
    val game = MinesWeeper.of(height, width, mineCounts)
    game.calculateCount()

    ResultView.printGameStart()

    while (!game.isEnd()) {
        val openingLocation = InputView.getOpeningLocation(height, width)
        game.openCell(openingLocation)
        ResultView.printRoundResult(height, width, game)
    }
}
