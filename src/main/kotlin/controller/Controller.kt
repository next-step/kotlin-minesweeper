package controller

import domain.Board
import presentation.InputView

fun main() {
    val board = Board(
        height = InputView.getHeight(),
        width = InputView.getWidth(),
        mineCount = InputView.getMineCount(),
    )
}
