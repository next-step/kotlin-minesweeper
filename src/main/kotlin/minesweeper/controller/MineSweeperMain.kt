package minesweeper.controller

import minesweeper.view.InputView

fun main() {
    val rows = InputView.getCols()
    val cols = InputView.getRows()
    val mine = InputView.getMine()
}
