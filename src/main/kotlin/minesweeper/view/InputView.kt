package minesweeper.view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }
}
