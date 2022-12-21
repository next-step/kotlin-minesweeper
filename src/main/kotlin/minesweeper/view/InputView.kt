package minesweeper.view

object InputView {
    fun readHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun readWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun readMineCount(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }
}
