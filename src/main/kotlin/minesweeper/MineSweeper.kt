package minesweeper

import minesweeper.domain.BlockMap

fun main() {
    val height = InputView.height()
    val width = InputView.width()
    val mineCount = InputView.mineCount()
    val blackMap = BlockMap(height, width, mineCount)
}
