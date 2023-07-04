package controller

import domain.MinesWeeper
import view.InputView

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCounts = InputView.getMineCounts()

    val game = MinesWeeper.of(height, width, mineCounts)
}
