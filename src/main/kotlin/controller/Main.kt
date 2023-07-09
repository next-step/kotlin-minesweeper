package controller

import domain.MinesWeeper
import view.InputView
import view.ResultView

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCounts = InputView.getMineCounts()
    ResultView.printGameStart()
    val openLocation = InputView.getOpeningLocation()

    val game = MinesWeeper.of(height, width, mineCounts)
    game.calculateCount()
    ResultView.printGameBoard(height, width, game)
}
