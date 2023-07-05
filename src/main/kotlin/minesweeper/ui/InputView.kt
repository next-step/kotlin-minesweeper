package minesweeper.ui

class InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }
}
