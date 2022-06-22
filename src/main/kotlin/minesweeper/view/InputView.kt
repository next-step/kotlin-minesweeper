package minesweeper.view

interface InputView {
    fun printHeightInputMessage()

    fun inputHeight(): Int

    fun printWidthInputMessage()

    fun inputWidth(): Int

    fun printMineCountInputMessage()

    fun inputMineCount(): Int
}
