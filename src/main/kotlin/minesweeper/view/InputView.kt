package minesweeper.view

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("높이는 숫자여야 합니다.")
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("너비 숫자여야 합니다.")
    }
}
