package minesweeper.view

object ConsoleInputView : InputView {
    override fun printHeightInputMessage() = println("높이를 입력하세요.")

    override fun inputHeight(): Int = readln().toInt()

    override fun printWidthInputMessage() = println("\n너비를 입력하세요.")

    override fun inputWidth(): Int = readln().toInt()

    override fun printMineCountInputMessage() = println("\n지뢰는 몇 개인가요?")

    override fun inputMineCount(): Int = readln().toInt()
}
