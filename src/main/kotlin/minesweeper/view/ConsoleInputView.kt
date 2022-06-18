package minesweeper.view

object ConsoleInputView : InputView {
    override fun printHeightInputMessage() = println("높이를 입력하세요.")

    override fun inputHeight() = readln().toInt()

    override fun printWidthInputMessage() = println("\n너비를 입력하세요.")

    override fun inputWidth() = readln().toInt()

    override fun printMineCountInputMessage() = println("\n지뢰는 몇 개인가요?")

    override fun inputMineCount() = readln().toInt()
}
