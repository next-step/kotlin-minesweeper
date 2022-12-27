package minesweeper.ui

class ConsoleInputView : InputView {
    override fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    override fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    override fun inputLandMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
