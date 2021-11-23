package minesweeper.view

object InputView {
    fun inputWidth(): Int {
        println("높이를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun inputHeight(): Int {
        println("너비를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun inputMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }
}
