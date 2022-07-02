package minesweeper.view

import minesweeper.model.CellPosition

interface InputView {
    fun printHeightInputMessage()

    fun inputHeight(): Int

    fun printWidthInputMessage()

    fun inputWidth(): Int

    fun printMineCountInputMessage()

    fun inputMineCount(): Int

    fun inputOpenCellPosition(): CellPosition
}
