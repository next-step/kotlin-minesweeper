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

    while (!game.isSuccess()) {
        var openingLocation = InputView.getOpeningLocation(height, width)
        if (game.isMine(openingLocation)) {
            break
        }
        game.openCell(openingLocation)
        ResultView.printGameBoard(height, width, game)
    }
}