package minesweeper.ui

object InputView {

    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("높이는 공백일 수 없습니다.")
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toInt() ?: throw IllegalArgumentException("높이는 공백일 수 없습니다.")
    }

    fun inputNumberOfMine(): Int {
        println("지뢰의 개수를 입력해주세요")
        return readLine()?.toInt() ?: 0
    }
}