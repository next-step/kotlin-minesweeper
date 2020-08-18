package minesweeper.view

object InputView {
    fun getHeight(): Int {
        print("높이를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun getWidth(): Int {
        print("너비를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun getMineCount(): Int {
        print("지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }
}
