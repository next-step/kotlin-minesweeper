package minesweeper

import minesweeper.view.InputView

fun main() {
    val height = InputView.receiveHeight()
    val width = InputView.receiveWidth()
    val mineCount = InputView.receiveMineCount()
}
