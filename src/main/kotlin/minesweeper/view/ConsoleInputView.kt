package minesweeper.view

import minesweeper.model.CellPosition

object ConsoleInputView : InputView {
    private const val CELL_POSITION_DELIMITER = ","

    override fun printHeightInputMessage() = println("높이를 입력하세요.")

    override fun inputHeight(): Int = readln().toInt()

    override fun printWidthInputMessage() = println("\n너비를 입력하세요.")

    override fun inputWidth(): Int = readln().toInt()

    override fun printMineCountInputMessage() = println("\n지뢰는 몇 개인가요?")

    override fun inputMineCount(): Int = readln().toInt()

    override fun inputOpenCellPosition(): CellPosition {
        print("open: ")

        val (y, x) = readln().split(CELL_POSITION_DELIMITER)
        return CellPosition.of(x.trim().toInt(), y.trim().toInt())
    }
}
