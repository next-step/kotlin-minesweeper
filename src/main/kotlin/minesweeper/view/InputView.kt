package minesweeper.view

object InputView {
    fun inputHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }

    fun inputCountOfMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
