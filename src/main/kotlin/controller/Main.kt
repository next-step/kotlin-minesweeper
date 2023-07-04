package controller

import domain.MineGenerator
import domain.Mines
import view.InputView

fun main() {
    val height = InputView.getHeight()
    val width = InputView.getWidth()
    val mineCounts = InputView.getMineCounts()

    val game = MineGenerator.create(height, width, mineCounts)
}
