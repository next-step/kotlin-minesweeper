package minesweeper.controller

import minesweeper.domain.MineSweeper
import minesweeper.domain.Mines
import minesweeper.domain.Size
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val rowNumber = InputView.getCols()
    val colNumber = InputView.getRows()
    val mineNumber = InputView.getMine()
    val mines = Mines.generateMine(mineNumber, Size(colNumber, rowNumber))
    val map = MineSweeper.getMap(mines, Size(colNumber, rowNumber))
    ResultView.start(map)
}
