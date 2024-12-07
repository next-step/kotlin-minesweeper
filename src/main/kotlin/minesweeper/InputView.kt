package minesweeper

object InputView {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }

    fun getWidth(): Int {
        println("\n너비를 입력하세요.")
        return readln().toInt()
    }

    fun getMineCount(): Int {
        println("\n지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
