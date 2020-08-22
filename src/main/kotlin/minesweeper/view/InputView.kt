package minesweeper.view

object InputView {
    fun getHeight(): Int {
        print("높이(row)를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun getWidth(): Int {
        print("너비(column)를 입력하세요.")
        return readLine()!!.toInt()
    }

    fun getMineCount(): Int {
        print("지뢰는 몇 개인가요?")
        return readLine()!!.toInt()
    }
}
