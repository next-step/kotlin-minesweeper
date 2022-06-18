package minesweeper.view

import minesweeper.model.MineField

interface ResultView {
    fun printMineField(field: MineField)
}
